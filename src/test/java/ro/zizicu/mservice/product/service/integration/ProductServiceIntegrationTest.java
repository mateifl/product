package ro.zizicu.mservice.product.service.integration;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.BaseIntegrationTest;
import ro.zizicu.mservice.product.entities.*;
import ro.zizicu.mservice.product.services.ProductService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductServiceIntegrationTest extends BaseIntegrationTest {

	@Autowired
	private ProductService productService;

	@Test
	void createProductTestSuccess() {
		try {
			Product product = new Product();
			product.setName("Test Integration");
			product.setDiscontinued(0);
			Category category = new Category();
			category.setId(1);
			product.setCategory(category);
			Supplier supplier = new Supplier();
			supplier.setId(1);
			product.setSupplier(supplier);
			Product created = productService.create(product);
			assertNotNull(created, "Product created must not be null");

			
			product = new Product();
			product.setName("Test integration 1");
			product.setUnitPrice(11.12d);
			product.setQuantityPerUnit("5 pieces");
			product.setUnitsInStock(100);
			product.setUnitsOnOrder(23);
			product.setDiscontinued(0);
			category = new Category();
			category.setId(1);
			product.setCategory(category);
			supplier = new Supplier();
			supplier.setId(1);
			product.setSupplier(supplier);
			created = productService.create(product);
			assertNotNull(created, "Product created must not be null");

		}
		catch(Exception e) {
			fail(e);
		}
	}
	
	@Test
	void createProductTestNoCategory() {
		try {
			Product product = new Product();
			product.setName("Test Integration Should fail");
			Supplier supplier = new Supplier();
			supplier.setId(1);
			product.setSupplier(supplier);
			product.setDiscontinued(0);
			productService.create(product);
			fail("product creation should fail");
		} catch (Exception e ) {
			log.info("passed");
			assertTrue(true);
		}
	}
	
	@Test
	void filterProductTestByName() {
		List<Product> optionalProductList = productService.find("%Cam%", null, null);
		Assertions.assertFalse(optionalProductList.isEmpty());
	}
	
	@Test
	void filterProductTestByNameAndCategory() {
		List<Product> optionalProductList = productService.find("%Cam%", 4, null);
		Assertions.assertFalse(optionalProductList.isEmpty());
	}
	
	@Test
	void filterProductTestByNameAndCategoryNoResults() {
		List<Product> optionalProductList = productService.find("Ch%", 4, null);
		Assertions.assertTrue(optionalProductList.isEmpty());
	}
}
