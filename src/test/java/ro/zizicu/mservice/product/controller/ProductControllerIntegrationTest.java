package ro.zizicu.mservice.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;
import ro.zizicu.mservice.product.BaseIntegrationTest;
import ro.zizicu.mservice.product.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnFilteredProducts() throws Exception {
        MvcResult result  =  mockMvc.perform(get("/products/find")
                        .param("name", "Tofu"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tofu"))
                .andReturn();
    }

    @Test
    void shouldCreateProduct_andReturn201_andLocationHeader() throws Exception {
        ProductDto requestDto = new ProductDto();
        requestDto.setName("Laptop");
        requestDto.setCategoryId(1);
        requestDto.setSupplierId(2);
        requestDto.setDiscontinued(1);

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(result -> {
                    log.info(result.getResponse().getContentAsString());
                })
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.supplierId").value(2));
    }
}
