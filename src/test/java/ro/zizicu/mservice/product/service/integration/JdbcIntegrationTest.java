package ro.zizicu.mservice.product.service.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.zizicu.mservice.product.data.UtilRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class JdbcIntegrationTest {

    @Autowired
    private UtilRepository utilRepository;

    @Test
    public void testNextTransactionId() {
        Long transactionId = utilRepository.getTransactionId();
        log.info("transaction id: {}", transactionId);
        assertNotNull(transactionId);
    }

}
