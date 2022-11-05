package ro.zizicu.mservice.product.services.distibuted.transaction;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ro.zizicu.mservice.product.services.support.TransactionStep;
import ro.zizicu.nwbase.transaction.TransactionMessage;

import java.util.Map;

@RequiredArgsConstructor
@Data
@Slf4j
@Service
public class DefaultTransactionStepExecutor  {

    private final PlatformTransactionManager transactionManager;
    private final KafkaTemplate<String, TransactionMessage> kafkaTemplate;
    private final Map<Long, TransactionStep> distributedTransactionMap;

    public void executeOnDatabase(TransactionStep transactionStep) {
        log.debug("executing transaction {}", transactionStep.getTransactionId());
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setTimeout(3);
        TransactionStatus status = getTransactionManager().getTransaction(definition);
        transactionStep.setTransactionStatus(status);
        transactionStep.execute();
        getDistributedTransactionMap().put(transactionStep.getTransactionId(), transactionStep);
        log.debug("sending transaction message {}", transactionStep.getTransactionId());
        getKafkaTemplate().send("", TransactionMessage.builder()
                .transactionId(transactionStep.getTransactionId())
                .serviceName(transactionStep.getServiceName())
                .build());

    }

    public void commit(Long transactionId) {
        log.debug("committing transaction {}", transactionId);
        transactionManager.commit(distributedTransactionMap.get(transactionId).getTransactionStatus());
        distributedTransactionMap.remove(transactionId);
        log.debug("transaction {} committed", transactionId);
    }

    public void rollback(Long transactionId) {
        log.debug("rollback transaction {}", transactionId);
        transactionManager.rollback(distributedTransactionMap.get(transactionId).getTransactionStatus());
        distributedTransactionMap.remove(transactionId);
        log.debug("transaction {} rolled back", transactionId);
    }

}
