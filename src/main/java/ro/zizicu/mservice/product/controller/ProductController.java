package ro.zizicu.mservice.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.mservice.product.services.distibuted.transaction.DefaultTransactionStepExecutor;
import ro.zizicu.mservice.product.services.distibuted.transaction.UpdateProductStock;
import ro.zizicu.nwbase.controller.NamedEntityController;
import ro.zizicu.nwbase.rest.TransactionCoordinatorRestClient;
import ro.zizicu.nwbase.transaction.TransactionStatus;

@RestController
@RequestMapping(value = "/products")
@Slf4j
public class ProductController
	extends NamedEntityController<Product, Integer> {

	private final ProductService productService;
	private final DefaultTransactionStepExecutor transactionStepExecutor;
	private final UpdateProductStock updateProductStock;
	private final TransactionCoordinatorRestClient restClient;
	
	public ProductController(ProductService productService,
							 DefaultTransactionStepExecutor transactionStepExecutor,
							 UpdateProductStock updateProductStock,
							 TransactionCoordinatorRestClient restClient) {
		super(productService);
		this.productService = productService;
		this.transactionStepExecutor = transactionStepExecutor;
		this.updateProductStock = updateProductStock;
		this.restClient = restClient;
	}

	@Override
	protected String getLocation() {
		return "products";
	}
	
	@GetMapping(value = "/find")
	public ResponseEntity<?> find(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) Integer supplierId) {
		log.debug("filtering products");
		Optional<List<Product>> products =  productService.find(name, categoryId, supplierId);
		if(products.isPresent())
			return ResponseEntity.ok().body(products);
		else
			return ResponseEntity.notFound().build();
	}

	@PatchMapping(value="/update-stock/{transactionId}")
	public ResponseEntity<?> updateStock(@RequestBody Product product, @PathVariable Long transactionId) {
		log.debug("transaction id {}", transactionId);
		updateProductStock.setProduct(product);
		transactionStepExecutor.executeOnDatabase(updateProductStock, transactionId);
		TransactionStatus transactionStatus = checkTransactionStatus(transactionId);
		if(transactionStatus == TransactionStatus.READY_TO_COMMIT)
		{
            log.debug("transaction ready to commit");
            transactionStepExecutor.commit(transactionId);
            ResponseEntity.ok().body("transaction commited " + transactionId);
		}
		if(transactionStatus == TransactionStatus.ROLLEDBACK)
		{
            log.debug("transaction ready to rollback");
            ResponseEntity.status(500).body("transaction rolledback " + transactionId);
		}
		
		return ResponseEntity.ok().body(product);
	}

	private TransactionStatus checkTransactionStatus(Long transactionId) {
		int counter = 0;
		try {
			while (true) {
				Thread.sleep(10);
				if(counter % 100 == 0)
					log.debug("checking transaction status {}", transactionId);
				TransactionStatus transactionStatus = restClient.getDistributedTransactionStatus(transactionId).getStatus();
				
				if(transactionStatus == TransactionStatus.READY_TO_COMMIT || transactionStatus == TransactionStatus.ROLLEDBACK) {
					return transactionStatus;
				}
				counter += 1;
				if (counter == 1000)
				{
					log.debug("end polling, transaction rollback");
					return TransactionStatus.ROLLEDBACK;
				}
			}

		}
		catch(InterruptedException e) {
			log.error(e.getMessage());
			return TransactionStatus.ROLLEDBACK;
		}
		
	}
	
}
