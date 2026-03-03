package ro.zizicu.mservice.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.zizicu.mservice.product.BaseIntegrationTest;
import ro.zizicu.mservice.product.entities.Supplier;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SupplierControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Emag");
        supplier.setCity("London");
        supplier.setCountry("United Kingdom");
        supplier.setAddress("Trafalgar Square no. 1");
        supplier.setPhone("4034567890");
        String jsonRequest = objectMapper.writeValueAsString(supplier);
        mockMvc.perform(post("/suppliers").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Emag"))
                .andExpect(jsonPath("$.phone").value("4034567890"))
                .andReturn();
    }
}
