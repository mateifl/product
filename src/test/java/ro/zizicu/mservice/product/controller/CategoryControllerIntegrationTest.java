package ro.zizicu.mservice.product.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.zizicu.mservice.product.BaseIntegrationTest;
import ro.zizicu.mservice.product.entities.Category;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CategoryControllerIntegrationTest extends BaseIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetCategoryByName() {
        ResponseEntity<List> response = restTemplate.getForEntity( "/categories/name/Beverages", List.class );
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().size());

    }

    @Test
    void shouldCreateCategory() throws Exception {
        Category category = new Category();
        category.setName("Fruits");
        category.setDescription("Fruits");
        String jsonRequest = objectMapper.writeValueAsString(category);
        MvcResult result  =  mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Fruits"))
                .andReturn();
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        Category  category = new Category();
        category.setName("Fruits2");
        category.setDescription("Fruits2");
        String jsonRequest = objectMapper.writeValueAsString(category);

        MvcResult result  =  mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Fruits2"))
                .andReturn();
        Category created = objectMapper.readValue(result.getResponse().getContentAsString(), Category.class);

        mockMvc.perform(delete("/categories/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
