package ro.zizicu.mservice.product.services;

import ro.zizicu.nwbase.transaction.TransactionStatus;
import ro.zizicu.nwbase.transaction.TransactionStatusMessage;

public interface RestClient {

    void sendTransactionStepStatus(Long transactionId, TransactionStatus status);

    TransactionStatusMessage getDistributedTransactionStatus(Long transactionId);

}
