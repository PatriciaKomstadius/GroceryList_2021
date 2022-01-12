package se.iths.grocerylist;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import se.iths.grocerylist.entity.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class GroceryListApplicationTests {



    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;


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

        assertThat(result.getBody().getUsername().equals("admin"));

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FOUND);

    }


    @Test
    void deleteUserShouldReturnNoContent() {
        String url = "http://localhost:" + port + "/users/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "123");

        long id = 4;
        var result = testClient.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers),UserEntity.class,id);



        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void createdGrocerylistShoudeReturnStatusCreated(){

        String url = "http://localhost:" + port + "/grocerylists";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "123");
        headers.add("Content-Type", "application/json");


        GroceryListEntity groceryListEntity = new GroceryListEntity("Julmat");
       var result = testClient.exchange(url, HttpMethod.POST, new HttpEntity<Object>(groceryListEntity, headers), GroceryListEntity.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }
}
