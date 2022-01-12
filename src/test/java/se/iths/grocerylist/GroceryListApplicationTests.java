package se.iths.grocerylist;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import se.iths.grocerylist.entity.*;
import se.iths.grocerylist.repository.GroceryListRepository;
import se.iths.grocerylist.repository.RoleRepository;
import se.iths.grocerylist.repository.UserRepository;
import se.iths.grocerylist.view.MvcConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class GroceryListApplicationTests {



    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeTestLoadDatabaseWithUsers(){


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
        String url = "http://localhost:" + port + "/users/{id}";



        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "123");

        long id = 4;
        var result = testClient.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),UserEntity.class,id);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FOUND);


    }

}
