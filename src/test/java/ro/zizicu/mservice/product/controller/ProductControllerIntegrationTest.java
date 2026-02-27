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
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.entities.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.zizicu.nwbase.controller.request.UpdateStockRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
        Product requestDto = new Product();
        requestDto.setName("Laptop");
        Category category = new Category();
        category.setId(1);
        requestDto.setCategory(category);
        Supplier supplier = new Supplier();
        supplier.setId(1);
        requestDto.setSupplier(supplier);
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
                .andExpect(jsonPath("$.category.id").value(1))
                .andExpect(jsonPath("$.supplier.id").value(1));
    }

    @Test
    void shouldUpdateStock_andReturn201_andLocationHeader() throws Exception {
        UpdateStockRequest  updateStockRequest = new UpdateStockRequest();
        updateStockRequest.setId(6);
        updateStockRequest.setUnitsOnOrder(2);
        String jsonRequest = objectMapper.writeValueAsString(updateStockRequest);

        mockMvc.perform(patch("/products/update-stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isNoContent());

    }


}
