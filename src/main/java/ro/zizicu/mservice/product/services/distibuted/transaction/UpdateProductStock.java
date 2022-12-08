package ro.zizicu.mservice.product.services.distibuted.transaction;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.support.TransactionStep;


@Slf4j
@RequiredArgsConstructor
@Data
@Component
@RequestScope
public class UpdateProductStock implements TransactionStep {
    private final ProductRepository productRepository;
    private Product product;
    
    @Override
    public void execute() {
        log.debug("save product {}", product);
        productRepository.save(product);
        log.debug("product saved");
    }

}
