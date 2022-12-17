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
import ro.zizicu.mservice.product.services.distibuted.transaction.UpdateProductStock;
import ro.zizicu.nwbase.controller.NamedEntityController;
import ro.zizicu.nwbase.rest.TransactionCoordinatorRestClient;
import ro.zizicu.nwbase.service.DistributedTransactionService;
import ro.zizicu.nwbase.transaction.DistributedTransactionStatus;
import ro.zizicu.nwbase.transaction.support.DefaultTransactionStepExecutor;

@RestController
@RequestMapping(value = "/products")
@Slf4j
public class ProductController
	extends NamedEntityController<Product, Integer> {

	private final ProductService productService;
	private final DistributedTransactionService distributedTransactionService;
	private final UpdateProductStock updateProductStock;
	
	public ProductController(ProductService productService,
							 DistributedTransactionService distributedTransactionService,
							 UpdateProductStock updateProductStock,
							 TransactionCoordinatorRestClient restClient) {
		super(productService);
		this.productService = productService;
		this.distributedTransactionService = distributedTransactionService;
		this.updateProductStock = updateProductStock;

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
		log.debug("transaction id {} service object {}", transactionId, updateProductStock);
		updateProductStock.setProduct(product);
		distributedTransactionService.executeTransactionStep(updateProductStock, transactionId);
		return ResponseEntity.ok().body(product);
	}
}
