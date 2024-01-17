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
import ro.zizicu.nwbase.controller.NamedEntityController;


@RestController
@RequestMapping(value = "/products")
@Slf4j
public class ProductController
	extends NamedEntityController<Product, Integer> {

	private final ProductService productService;

	
	public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
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

		return ResponseEntity.ok().body(product);
	}

}
