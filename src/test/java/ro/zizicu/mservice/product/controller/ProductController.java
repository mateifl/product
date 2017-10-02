package ro.zizicu.mservice.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "test";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String createProduct() {
		return "This is a test";
	}
	
	public void getProduct() {
		
	}
	
	public void updateProduct() {
		
	}
	
}
