package ro.zizicu.mservice.product.services.distibuted.transaction;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.transaction.support.AbstractTransactionStep;


@Slf4j
@Setter
@RequiredArgsConstructor
public class UpdateProductStock extends AbstractTransactionStep {
    
    private Product product;

    @Override
    public void execute() {
    	lastStep = Boolean.FALSE;
    	setServiceName("product");
    	Product fromDatabase = entityManager.find(Product.class, product.getId()); 
    	fromDatabase.setUnitsInStock(product.getUnitsInStock());
        log.debug("save product {}", fromDatabase);
        entityManager.persist(fromDatabase);
        log.debug("product saved");
    }

}
