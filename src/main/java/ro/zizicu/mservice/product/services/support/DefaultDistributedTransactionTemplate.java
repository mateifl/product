package ro.zizicu.mservice.product.services.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ro.zizicu.nwbase.transaction.TransactionMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultDistributedTransactionTemplate implements DistributedTransactionTemplate {


    private final PlatformTransactionManager transactionManager;

    private final KafkaTemplate<String, TransactionMessage> kafkaTemplate;

    @Override
    public void doInDistributedTransaction(TransactionStep step, Integer ... transactionId) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setTimeout(3);
        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            step.execute();
            if(transactionId == null) {
                log.debug("generate distributed transaction id (first step)");
            }
            else
                log.debug("distributed transaction id {}", transactionId);

            kafkaTemplate.send("stockUpdateTopic", TransactionMessage.builder().transactionId(1).build());
        }
        catch(RuntimeException e) {
            log.error(e.getMessage());
        }

    }
}
