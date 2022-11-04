package ro.zizicu.mservice.product.services.distibuted.transaction;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.support.DistributedTransaction;
import ro.zizicu.mservice.product.services.support.DistributedTransactionStatus;
import ro.zizicu.nwbase.transaction.TransactionMessage;

import java.util.Map;

@Service
@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class UpdateProductFromOrderTransaction implements DistributedTransaction {

    private final PlatformTransactionManager transactionManager;
    private final KafkaTemplate<String, TransactionMessage> kafkaTemplate;
    private final ProductRepository productRepository;
    private final Map<Long, DistributedTransaction> distributedTransactionMap;

    private TransactionStatus status;
    private Long transactionId;
    private final static String serviceName = "Product";
    private DistributedTransactionStatus distributedTransactionStatus = DistributedTransactionStatus.NOT_STARTED;

    public void executeOnDatabase(Product product) {
        log.debug( "distributed transaction object id {}", this.toString() );
        this.transactionId = productRepository.getTransactionId();
        log.debug("executing transaction {}", transactionId);
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setTimeout(3);
        status = transactionManager.getTransaction(definition);
        productRepository.save(product);
        distributedTransactionMap.put(transactionId, this);
        log.debug("sending transaction message {}", transactionId);
        kafkaTemplate.send("", TransactionMessage.builder().transactionId(transactionId).serviceName(serviceName).build());
        distributedTransactionStatus = DistributedTransactionStatus.STARTED;
    }

    @Override
    public void commit() {
        log.debug("committing transaction {}", transactionId);
        transactionManager.commit(status);
        distributedTransactionMap.remove(transactionId);
        distributedTransactionStatus = DistributedTransactionStatus.COMMITTED;
    }

    public DistributedTransactionStatus getDistributedTransactionStatus() {
        return distributedTransactionStatus;
    }
}
