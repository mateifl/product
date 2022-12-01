package ro.zizicu.mservice.product.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.zizicu.mservice.product.services.RestClient;
import ro.zizicu.nwbase.transaction.TransactionStatus;
import ro.zizicu.nwbase.transaction.TransactionStatusMessage;

@AllArgsConstructor
@Service
public class TransactionCoordinatorRestClient implements RestClient {

    private final Environment environment;
    private final RestTemplate restTemplate;
    @Override
    public void sendTransactionStepStatus(Long transactionId, TransactionStatus status) {
        String transactionCoordinatorUrl = environment.getProperty("transactionCoordinator.url");
        HttpEntity<TransactionStatus> updatedEntity = new HttpEntity<TransactionStatus>(status);
        restTemplate.patchForObject(transactionCoordinatorUrl + "/",
                updatedEntity,
                TransactionStatus.class);
    }

    @Override
    public TransactionStatusMessage getDistributedTransactionStatus(Long transactionId) {
        String transactionCoordinatorUrl = environment.getProperty("transactionCoordinator.url");
        TransactionStatusMessage message = restTemplate.getForObject( transactionCoordinatorUrl + "/" + transactionId, TransactionStatusMessage.class );
        return message;
    }
}
