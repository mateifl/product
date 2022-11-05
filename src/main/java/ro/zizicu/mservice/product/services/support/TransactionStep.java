package ro.zizicu.mservice.product.services.support;

import org.springframework.transaction.TransactionStatus;

public interface TransactionStep {
    void execute();
    Long getTransactionId();
    void setTransactionId(Long transactionId);

    String getServiceName();

    DistributedTransactionStatus getDistributedTransactionStatus();
    void setDistributedTransactionStatus(DistributedTransactionStatus distributedTransactionStatus);

    TransactionStatus getTransactionStatus();
    void setTransactionStatus(TransactionStatus transactionStatus);
}

