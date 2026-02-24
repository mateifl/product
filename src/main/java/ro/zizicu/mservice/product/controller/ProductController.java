package ro.zizicu.mservice.product.controller;

import java.net.URI;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.zizicu.mservice.product.dto.ProductDto;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;

@RestController
@RequestMapping(value = "/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController{

	private final ProductService productService;

	@GetMapping(value = "/find")
	public ResponseEntity<?> find(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) Integer supplierId) {
		log.debug("filtering products");
		List<ProductDto> products =  productService.find(name, categoryId, supplierId);
		return ResponseEntity.ok().body(products);
	}

	@PatchMapping(value="/update-stock/")
	public ResponseEntity<?> updateStock(@RequestBody Product product) {
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
		log.debug("create product");
		ProductDto createdProductDto = productService.create(productDto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdProductDto.getId())
				.toUri();

		return ResponseEntity.created(location).body(createdProductDto);
	}

}
