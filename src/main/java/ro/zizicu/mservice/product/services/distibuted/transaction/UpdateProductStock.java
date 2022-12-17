package ro.zizicu.mservice.product.services.distibuted.transaction;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.exceptions.ProductNotFound;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.transaction.support.TransactionStep;


@Slf4j
@RequiredArgsConstructor
@Setter
@Component
@RequestScope
public class UpdateProductStock implements TransactionStep {
    private final ProductRepository productRepository;
    private Product product;
    
    @Override
    public void execute() {
    	Optional<Product> fromDatabase = productRepository.findById(product.getId());
    	if(fromDatabase.isEmpty()) 
    		throw new ProductNotFound();
    	fromDatabase.get().setUnitsInStock(product.getUnitsInStock());
        log.debug("save product {}", fromDatabase.get());
        productRepository.save(fromDatabase.get());
        log.debug("product saved");
    }

}
