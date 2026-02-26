package ro.zizicu.mservice.product.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.nwbase.controller.NamedEntityController;

@RestController
@RequestMapping(value = "/products")
@Slf4j
public class ProductController extends NamedEntityController<Product, ProductService, Integer> {

	public ProductController(ProductService productService) {
		super(productService);
	}

	@GetMapping(value = "/find")
	public ResponseEntity<?> find(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) Integer supplierId) {
		log.debug("filtering products");
		List<Product> products =  ((ProductService)getService()).find(name, categoryId, supplierId);
		return ResponseEntity.ok().body(products);
	}

	@PatchMapping(value="/update-stock/")
	public ResponseEntity<?> updateStock(@RequestBody Product product) {
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		log.debug("create product");
		Product createdProduct = getService().create(product);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdProduct.getId())
				.toUri();

		return ResponseEntity.created(location).body(createdProduct);
	}

}
