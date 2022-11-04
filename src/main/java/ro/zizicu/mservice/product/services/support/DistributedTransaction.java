package ro.zizicu.mservice.product.services.support;

public interface DistributedTransaction {

    void commit();

    DistributedTransactionStatus getDistributedTransactionStatus();
}
