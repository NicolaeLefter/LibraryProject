package com.example.demo.integrationTest;

import com.example.demo.LibraryApplication;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @LocalServerPort
    Integer port;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();
    @Test
    public void userControllerAddUser() throws JSONException {
        User user = new User("Nicolae123","nicu200013");
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/user/save",
                HttpMethod.POST,
                httpEntity,
                String.class
        );
        String excepted = "Utilizatorul a fost adaugat cu succes!";

        JSONAssert.assertEquals(excepted, responseEntity.getBody(), false);
    }

    @Test
    public void userControllerGetAllTest() throws JSONException {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/user/getAll",
                HttpMethod.GET,
                httpEntity,
                String.class
        );
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"username\": \"Nicolae123\",\n" +
                "        \"password\": \"nicu200013\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"username\": \"CEITI\",\n" +
                "        \"password\": \"ceiti2020\"\n" +
                "    }\n" +
                "]";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }
}
