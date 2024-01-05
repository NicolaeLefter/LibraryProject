package com.example.demo.integrationTest;

import com.example.demo.LibraryApplication;
import com.example.demo.entity.Book;
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
public class BookIntegrationTest {

    @LocalServerPort
    Integer port;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void bookControllerGetAllTest() throws JSONException {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/carti/getAll",
                HttpMethod.GET,
                httpEntity,
                String.class
        );
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"DE LA IDEE LA BANI\",\n" +
                "        \"author\": \"Jim Howard\",\n" +
                "        \"price\": 100.50,\n" +
                "        \"stocQuantity\": null\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"title\": \"Carte 1\",\n" +
                "        \"author\": \"Autor 1\",\n" +
                "        \"price\": 20.99,\n" +
                "        \"stocQuantity\": null\n" +
                "    }\n" +
                "]";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

    @Test
    public void bookControllerAddBook() throws JSONException {

        Book book = new Book(3, "Carte 2", "Autor 2", 20.99, 20);
        HttpEntity<Book> httpEntity = new HttpEntity<>(book, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/carti/save",
                HttpMethod.POST,
                httpEntity,
                String.class
        );
        String excepted = "Cartea a fost adaugata cu succes!";

        JSONAssert.assertEquals(excepted, responseEntity.getBody(), false);

    }

}

