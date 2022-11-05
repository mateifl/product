package ro.zizicu.mservice.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;
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

	public ProductController(ProductService productService,
							 DefaultTransactionStepExecutor transactionStepExecutor,
							 UpdateProductStock updateProductStock) {
		super(productService);
		this.productService = productService;
		this.transactionStepExecutor = transactionStepExecutor;
		this.updateProductStock = updateProductStock;
	}

	@Override
	protected String getLocation() {
		return "products";
	}
	
	@GetMapping(value = "?name={name}&categoryId={categoryId}&supplierId={supplierId}")
	public ResponseEntity<?> find(@RequestParam String name, 
			@RequestParam Integer categoryId, 
			@RequestParam Integer supplierId) {
		log.debug("filtering products");
		Optional<List<Product>> products =  productService.find(name, categoryId, supplierId);
		if(products.isPresent())
			return ResponseEntity.ok().body(products);
		else
			return ResponseEntity.notFound().build();
	}

	@PatchMapping(value="?transactionId={transactionId}")
	public ResponseEntity<?> updateStock(@RequestBody Product product, @RequestParam Long transactionId) {
		updateProductStock.setProduct(product);
		updateProductStock.setTransactionId(transactionId);
		transactionStepExecutor.executeOnDatabase(updateProductStock);
		try {
			while (updateProductStock.getDistributedTransactionStatus() == DistributedTransactionStatus.STARTED)
				Thread.sleep(10);
		}
		catch(InterruptedException e) {
			log.error(e.getMessage());
			return ResponseEntity.ok().body("could not get transaction status");
		}

		if(updateProductStock.getDistributedTransactionStatus() == DistributedTransactionStatus.COMMITTED)
			return ResponseEntity.ok().body(product);
		else
			return ResponseEntity.internalServerError().body("update stock transaction failed id = " + transactionId);
	}

}
