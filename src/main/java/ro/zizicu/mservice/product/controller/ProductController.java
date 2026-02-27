package ro.zizicu.mservice.product.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.nwbase.controller.NamedEntityController;
import ro.zizicu.nwbase.controller.request.UpdateStockRequest;

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

	@PatchMapping(value="/update-stock")
	public ResponseEntity<Void> updateStock(@Valid @RequestBody UpdateStockRequest updateStockRequest) {
		Product product = new Product();
		product.setUnitsOnOrder(updateStockRequest.getUnitsOnOrder());
		product.setId(updateStockRequest.getId());
		((ProductService)getService()).updateStock(product);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		log.debug("create product");
		Product createdProduct = getService().create(product);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdProduct.getId())
				.toUri();

		return ResponseEntity.created(location).body(createdProduct);
	}

}