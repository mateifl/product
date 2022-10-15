package ro.zizicu.mservice.product.service.integration;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;

@SpringBootTest
@Slf4j
public class ProductServiceIntegrationTest {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private SupplierRepository supplierRepository;
	
	@Test
	void createProductTestSuccess() {
		try {
			log.debug("load category with id {}", 1);
			Category c = categoryRepository.findById(1).get();
			log.debug("load supplier with id {}", 1);
			Supplier s = supplierRepository.findById(1).get();
			Product product = new Product();
			product.setName("Test Integration");
			product.setDiscontinued(false);
			Product created = productService.create(product, c, s);
			assertNotNull(created, "Product created must not be null");
			productService.delete(created);
			
			product = new Product();
			product.setName("Test integration 1");
			product.setUnitPrice(11.12);
			product.setQuantityPerUnit("5 pieces");
			product.setUnitsInStock(100);
			product.setUnitsOnOrder(23);
			product.setDiscontinued(false);
			created = productService.create(product, c, s);
			productService.delete(created);
		}
		catch(Exception e) {
			fail(e);
		}
	}
	
	@Test
	void createProductTestNoCategory() {
		Product product = new Product();
		product.setName("Test Integration Should fail");
		product.setDiscontinued(false);
		log.debug("load supplier with id {}", 1);
		Supplier s = supplierRepository.findById(1).get();
		Product created = productService.create(product, null, s);
		assertNotNull(created);
		productService.delete(created);
	}
	
	@Test
	void filterProductTestByName() {
		Optional<List<Product>> optionalProductList = productService.find("Gu%", null, null);
		Assertions.assertFalse(optionalProductList.isEmpty());
		Assertions.assertFalse(optionalProductList.get().isEmpty());
	}
	
	@Test
	void filterProductTestByNameAndCategory() {
		Optional<List<Product>> optionalProductList = productService.find("Ch%", 1, null);
		Assertions.assertFalse(optionalProductList.isEmpty());
		Assertions.assertFalse(optionalProductList.get().isEmpty());
	}
	
	@Test
	void filterProductTestByNameAndCategoryNoResults() {
		Optional<List<Product>> optionalProductList = productService.find("Ch%", 4, null);
		Assertions.assertFalse(optionalProductList.isEmpty());
		assertTrue(optionalProductList.get().isEmpty());
	}
}
