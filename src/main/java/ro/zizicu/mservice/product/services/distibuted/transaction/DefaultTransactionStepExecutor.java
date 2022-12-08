package ro.zizicu.mservice.product.services.distibuted.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.services.RestClient;
import ro.zizicu.mservice.product.services.support.TransactionStep;

@RequiredArgsConstructor
@Data
@Slf4j
@Service
@RequestScope
public class DefaultTransactionStepExecutor  {

    private final PlatformTransactionManager transactionManager;
    private final RestClient restClient;
    private TransactionStatus transactionStatus;

    public void executeOnDatabase(TransactionStep transactionStep, Long transactionId) {
        log.debug("executing transaction {}", transactionId);
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setTimeout(3);
        transactionStatus = getTransactionManager().getTransaction(definition);
        transactionStep.execute();
        restClient.sendTransactionStepStatus(transactionId, ro.zizicu.nwbase.transaction.TransactionStatus.READY_TO_COMMIT);
        log.debug("transaction step {} executed", transactionId);
    }

    public void commit(Long transactionId) {
        log.debug("committing transaction {}", transactionId);
        transactionManager.commit(transactionStatus);
        log.debug("transaction {} committed", transactionId);
    }

    public void rollback(Long transactionId) {
        log.debug("rollback transaction {}", transactionId);
        transactionManager.rollback(transactionStatus);
        log.debug("transaction {} rolled back", transactionId);
    }

}
