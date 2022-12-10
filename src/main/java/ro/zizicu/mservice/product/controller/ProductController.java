package ro.zizicu.mservice.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.mservice.product.services.RestClient;
import ro.zizicu.mservice.product.services.distibuted.transaction.DefaultTransactionStepExecutor;
import ro.zizicu.mservice.product.services.distibuted.transaction.UpdateProductStock;
import ro.zizicu.nwbase.controller.NamedEntityController;
import ro.zizicu.nwbase.transaction.TransactionStatus;

@RestController
@RequestMapping(value = "/products")
@Slf4j
public class ProductController
	extends NamedEntityController<Product, Integer> {

	private final ProductService productService;
	private final DefaultTransactionStepExecutor transactionStepExecutor;
	private final UpdateProductStock updateProductStock;
	private final RestClient restClient;
	public ProductController(ProductService productService,
							 DefaultTransactionStepExecutor transactionStepExecutor,
							 UpdateProductStock updateProductStock,
							 RestClient restClient) {
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
		int counter = 0;
		try {
			while (true) {
				Thread.sleep(10);
				if(counter % 100 == 0)
					log.debug("checking transaction status {}", transactionId);
				TransactionStatus transactionStatus = restClient.getDistributedTransactionStatus(transactionId).getStatus();
				
				if(transactionStatus == TransactionStatus.READY_TO_COMMIT) {
					log.debug("transaction ready to commit");
					transactionStepExecutor.commit(transactionId);
					break;
				}
				else if(transactionStatus == TransactionStatus.ROLLEDBACK) {
					log.debug("transaction ready to rollback");
					transactionStepExecutor.rollback(transactionId);
					break;
				}
				counter += 1;
				if (counter == 1000)
				{
					log.debug("end polling, transaction rollback");
					transactionStepExecutor.rollback(transactionId);
					break;
				}
			}

		}
		catch(InterruptedException e) {
			log.error(e.getMessage());
			return ResponseEntity.ok().body("could not get transaction status");
		}
		return ResponseEntity.ok().body(product);
	}

}
