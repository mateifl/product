package ro.zizicu.mservice.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@Slf4j
public abstract class BaseIntegrationTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16").withInitScript("schema.sql");

    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // This dynamically maps the random port assigned by Docker to Spring
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

//    @Autowired
//    private ObjectMapper objectMapper;


//    protected void logJsonBody(MvcResult result) {
//        String json = null;
//        try {
//            json = result.getResponse().getContentAsString();
//            Object jsonObject = objectMapper.readValue(json, Object.class);
//            String prettyJson = objectMapper
//                .writerWithDefaultPrettyPrinter()
//                .writeValueAsString(jsonObject);
//            log.info(prettyJson);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
