package ro.zizicu.mservice.product.service.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.data.UtilRepository;

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
