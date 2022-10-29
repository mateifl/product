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
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.transaction.TransactionMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Scope(value = "prototype")
@RequiredArgsConstructor
@Slf4j
public class UpdateProductFromOrderTransaction {

    private final PlatformTransactionManager transactionManager;
    private final KafkaTemplate<String, TransactionMessage> kafkaTemplate;
    @PersistenceContext
    private EntityManager entityManager;
    private TransactionStatus status;


    public void executeOnDatabase(Product product, Integer transactionId) {

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setTimeout(3);
        TransactionStatus status = transactionManager.getTransaction(definition);
        entityManager.persist(product);
        kafkaTemplate.send("", TransactionMessage.builder().build());
    }




}
