package se.iths.grocerylist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.iths.grocerylist.entity.*;
import se.iths.grocerylist.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroceryListApplicationTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;

    @Autowired
    UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    void beforeTestLoadDatabaseWithUsers(String name) {

        String url = "http://localhost:" + port + "/users/signup";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        UserEntity userEntity = new UserEntity(name, "email", "admin", "b", passwordEncoder.encode("123"));
        var result = testClient.exchange(url, HttpMethod.POST, new HttpEntity<Object>(userEntity, headers), UserEntity.class);

    }

    @Test
    void makeAGetRequestWithoutLoginShouldReturnUnAuthorized() {
        String url = "http://localhost:" + port + "/users/{id}";

        long id = 1;
        var result = testClient.getForEntity(url, UserEntity.class, id);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

    }

    @Test
    void oneShouldReturnOneUsername() {
        beforeTestLoadDatabaseWithUsers("johan");

        String url = "http://localhost:" + port + "/users/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "123");

        long id = userRepository.findByUsername("johan").getId();

        var result = testClient.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), UserEntity.class, id);

        assertThat(result.getBody().getUsername().equals("johan"));

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @Test
    void deleteUserShouldReturnNoContent() {

        beforeTestLoadDatabaseWithUsers("kalle");

        String url = "http://localhost:" + port + "/users/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "123");

        long id = userRepository.findByUsername("kalle").getId();
        var result = testClient.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), UserEntity.class, id);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void createdGrocerylistShoudeReturnStatusCreated() {

        String url = "http://localhost:" + port + "/grocerylists";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "123");
        headers.add("Content-Type", "application/json");

        GroceryListEntity groceryListEntity = new GroceryListEntity("Julmat");
        var result = testClient.exchange(url, HttpMethod.POST, new HttpEntity<Object>(groceryListEntity, headers), GroceryListEntity.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }
}
