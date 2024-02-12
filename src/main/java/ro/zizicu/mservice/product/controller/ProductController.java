package ro.zizicu.mservice.product.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
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

	public ResponseEntity<?> create(ProductDto productDto) {
		log.debug("create product");
		ProductDto createdProductDto = productService.create(productDto);
		return ResponseEntity.ok().body(createdProductDto);
	}

}
