package ro.zizicu.mservice.product.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ro.zizicu.mservice.product.entities.Category;

@SpringBootTest
@Testcontainers
public class CategoryControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17");


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetCategoryByName() {
        ResponseEntity<Category> response = restTemplate.getForEntity( " categories/name/Beverages", Category.class );

    }


}
