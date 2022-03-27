package ro.zizicu.mservice.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.nwbase.controller.NamedEntityController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
@Slf4j
public class ProductController 
	extends NamedEntityController<Product, Integer> {

	private final ProductService productService;
	
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
	
	
}
