package ro.zizicu.mservice.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.controller.NamedEntityController;

@RestController
@RequestMapping(value = "products")
public class ProductController 
	extends NamedEntityController<Product, Integer> {

	@Override
	protected String getLocation() {
		return "products";
	}
	
}
