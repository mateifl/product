package ro.zizicu.mservice.product.services.distibuted.transaction;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ro.zizicu.mservice.product.services.support.DistributedTransaction;
import ro.zizicu.mservice.product.services.support.DistributedTransactionStatus;
import ro.zizicu.nwbase.transaction.TransactionMessage;

import java.util.Map;

@RequiredArgsConstructor
@Data
@Slf4j
public abstract class AbstractDistributedTransaction implements DistributedTransaction {

    private final PlatformTransactionManager transactionManager;
    private final KafkaTemplate<String, TransactionMessage> kafkaTemplate;
    private final Map<Long, DistributedTransaction> distributedTransactionMap;
    private final String serviceName;
    private TransactionStatus status;
    private Long transactionId;
    private DistributedTransactionStatus distributedTransactionStatus = DistributedTransactionStatus.NOT_STARTED;

    protected abstract void doInTransaction();

    public void executeOnDatabase(Long transactionId) {
        log.debug( "distributed transaction object id {}", this.toString() );
        log.debug("executing transaction {}", transactionId);
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setTimeout(3);
        status = transactionManager.getTransaction(definition);

        distributedTransactionMap.put(transactionId, this);
        log.debug("sending transaction message {}", transactionId);
        kafkaTemplate.send("", TransactionMessage.builder().transactionId(transactionId).serviceName(serviceName).build());
        distributedTransactionStatus = DistributedTransactionStatus.STARTED;
    }
}
