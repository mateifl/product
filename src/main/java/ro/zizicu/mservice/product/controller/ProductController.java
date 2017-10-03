package ro.zizicu.mservice.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.entities.Product;

@RestController
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(Product.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "test";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String createProduct() {
		return "This is a test";
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable Integer id) {
		logger.info("load product with id: " + id);
		Product product = productRepository.findOne(id);
		logger.debug("Supplier name: " + product.getSupplier().getCompanyName());
		return product;
	}
	
	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public void updateProduct() {
		
	}
	
}
