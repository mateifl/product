package ro.zizicu.mservice.product.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.data.CategoryRepository;
//import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;

@RestController
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(Product.class);
	
	@Autowired
	private ProductService productService;
	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired 
	private SupplierRepository supplierRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "test";
	}
	
	@RequestMapping(value = "/product/create/{categoryId}/{supplierId}", method = RequestMethod.POST)
	public ResponseEntity<?> createProduct(@PathVariable Integer categoryId, @PathVariable Integer supplierId,@RequestBody Product product) {
		Category category = categoryRepository.findOne(categoryId);
		if(category == null) 
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
		else logger.debug("Category loaded");
		Supplier s = supplierRepository.findOne(supplierId);
		if(s == null) 
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
		else logger.debug("Supplier loaded");
		product.setCategory(category);
		product.setSupplier(s);
		productService.createProduct(product, category, s);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable Integer id) {
		logger.info("load product with id: " + id);
		Product product = productService.loadProduct(id);
		logger.debug("Supplier name: " + product.getSupplier().getCompanyName());
		return product;
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public void updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		
	}
	
}
