package ro.zizicu.mservice.product.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.mservice.product.services.SupplierService;
import ro.zizicu.nwbase.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "products")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping(value = "/")
	public Iterable<Product> getProducts() {
		return productService.getProducts();
	}
	
	@PostMapping(value = "/{categoryId}/{supplierId}")
	public ResponseEntity<?> createProduct(@PathVariable Integer categoryId, @PathVariable Integer supplierId, @RequestBody Product product) {
		Category category = categoryService.load(categoryId);
		if(category == null) 
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
		else if(logger.isDebugEnabled())
			logger.debug("Category loaded");
		
		Supplier supplier = supplierService.load(supplierId);
		if(supplier == null) 
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
		else if(logger.isDebugEnabled())
			logger.debug("Supplier loaded");
		
		product.setCategory(category);
		product.setSupplier(supplier);
		product = productService.create(product, category, supplier);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping(value = "/{id}")
	public Product getProduct(@PathVariable Integer id) {
		logger.info("load product with id: " + id);
		Product product = productService.load(id);
		logger.debug("Supplier name: " + product.getSupplier().getName());
		return product;
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		logger.debug("update product: " + id);
		product.setId(id);
		try {
			product = productService.update(product);
			return ResponseEntity.ok(product);
		}
		catch(EntityNotFoundException e) {
			String errorMessage = "product not found, id:" + id;
			logger.error(errorMessage);
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
}
