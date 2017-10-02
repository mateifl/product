package ro.zizicu.mservice.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ro.zizicu.mservice.product.controller.ProductController;

@SpringBootApplication
//@ComponentScan(basePackageClasses = ProductController.class)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}
