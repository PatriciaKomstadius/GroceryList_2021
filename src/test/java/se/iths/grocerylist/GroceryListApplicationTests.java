//package se.iths.grocerylist;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import se.iths.grocerylist.entity.UserEntity;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class GroceryListApplicationTests {
//
//    @LocalServerPort
//    int port;
//
//    @Autowired
//    TestRestTemplate testClient;
//
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    @BeforeEach
//    void beforeTestLoadDatabaseWithUsers(){
//        UserEntity user1 = new UserEntity("admin", "ad@email", "admin", "a", passwordEncoder.encode("123"));
//        UserEntity user2 = new UserEntity("customer", "cu@email", "customer", "c", passwordEncoder.encode("123"));
//        UserEntity user3 = new UserEntity("employee", "em@email", "employee", "e", passwordEncoder.encode("123"));
//
//        testClient.postForEntity("http://localhost:" + port + "/users/signup", user1, UserEntity.class);
//        testClient.postForEntity("http://localhost:" + port + "/users/signup", user2, UserEntity.class);
//        testClient.postForEntity("http://localhost:" + port + "/users/signup", user3, UserEntity.class);
//
//    }
//
//
//    @Test
//    void makeAGetRequestWithoutLoginShouldReturnUnAuthorized() {
//        String url = "http://localhost:" + port + "/users/{id}";
//
//        long id = 1;
//        var result = testClient.getForEntity(url, UserEntity.class, id);
//
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//
//
//    }
//
//    @Test
//    void oneShouldReturnOneUsername() {
//        String url = "http://localhost:" + port + "/users/{id}";
//
//        long id = 1;
//        var result = testClient.getForEntity(url, UserEntity.class, id);
//
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(result.getBody().getUsername()).isEqualTo("admin");
//
//    }
//
//}
