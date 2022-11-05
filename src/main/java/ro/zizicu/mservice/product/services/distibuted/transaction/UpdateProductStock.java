package ro.zizicu.mservice.product.services.distibuted.transaction;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.support.DistributedTransactionStatus;
import ro.zizicu.mservice.product.services.support.TransactionStep;


@Slf4j
@RequiredArgsConstructor
@Data
@Component
@Scope("prototype")
public class UpdateProductStock implements TransactionStep {
    private final ProductRepository productRepository;
    private Product product;
    private Long transactionId;
    private DistributedTransactionStatus distributedTransactionStatus;
    private TransactionStatus transactionStatus;
    private final static String serviceName = "product";
    @Override
    public void execute() {
        productRepository.save(product);
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }
}
