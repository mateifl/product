package ro.zizicu.mservice.product.service.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import ro.zizicu.nwbase.transaction.TransactionMessage;

import java.util.concurrent.ExecutionException;


@SpringBootTest
@Slf4j
public class KafkaIntegrationTest {

    @Autowired
    private KafkaTemplate<String, TransactionMessage> kafkaTemplate;

    @Test
    public void testSendTransactionInformation() {
       ListenableFuture<SendResult<String, TransactionMessage>> future = kafkaTemplate.send( "stockUpdateTopic",
                TransactionMessage.builder().
                        transactionId(1821L).
                        isLastStep(false).
                        serviceName("Product").build() );

        try {
            log.info(String.valueOf(future.get().getProducerRecord().value()));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
