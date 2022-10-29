package ro.zizicu.mservice.product.services.support;

public interface DistributedTransactionTemplate {


    void doInDistributedTransaction( TransactionStep step, Integer ... transactionId );


}
