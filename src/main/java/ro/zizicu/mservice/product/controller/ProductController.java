package ro.zizicu.mservice.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.mservice.product.services.RestClient;
import ro.zizicu.mservice.product.services.distibuted.transaction.DefaultTransactionStepExecutor;
import ro.zizicu.mservice.product.services.distibuted.transaction.UpdateProductStock;
import ro.zizicu.mservice.product.services.support.DistributedTransactionStatus;
import ro.zizicu.nwbase.controller.NamedEntityController;

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

	@PatchMapping(value="/update-stock")
	public ResponseEntity<?> updateStock(@RequestBody Product product, @RequestParam Long transactionId) {
		updateProductStock.setProduct(product);
		transactionStepExecutor.executeOnDatabase(updateProductStock, transactionId);
		int counter = 0;
		try {
			while (restClient.getDistributedTransactionStatus(transactionId).getStatus() != null) {

				Thread.sleep(10);
				counter += 1;
				if (counter == 100000)
					break;
			}

		}
		catch(InterruptedException e) {
			log.error(e.getMessage());
			return ResponseEntity.ok().body("could not get transaction status");
		}


			return ResponseEntity.ok().body(product);
	}

}
