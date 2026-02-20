package ro.zizicu.mservice.product.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;
import ro.zizicu.mservice.product.BaseIntegrationTest;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
public class CategoryControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetCategoryByName() {
        ResponseEntity<List> response = restTemplate.getForEntity( "/categories/name/Beverages", List.class );
        Assertions.assertEquals(1, response.getBody().size());

    }
}
