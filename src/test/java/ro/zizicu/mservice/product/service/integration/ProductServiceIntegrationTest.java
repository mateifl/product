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
import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.dto.ProductDto;
import ro.zizicu.mservice.product.services.ProductService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductServiceIntegrationTest extends BaseIntegrationTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Test
	void createProductTestSuccess() {
		try {
			ProductDto product = new ProductDto();
			product.setName("Test Integration");
			product.setDiscontinued(0);
			product.setCategoryId(1);
			product.setSupplierId(1);
			ProductDto created = productService.create(product);
			assertNotNull(created, "Product created must not be null");

			
			product = new ProductDto();
			product.setName("Test integration 1");
			product.setUnitPrice(11.12f);
			product.setQuantityPerUnit("5 pieces");
			product.setUnitsInStock(100);
			product.setUnitsOnOrder(23);
			product.setDiscontinued(0);
			product.setCategoryId(1);
			product.setSupplierId(1);
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
			ProductDto product = new ProductDto();
			product.setName("Test Integration Should fail");
			product.setSupplierId(1);
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
		List<ProductDto> optionalProductList = productService.find("%Cam%", null, null);
		Assertions.assertFalse(optionalProductList.isEmpty());
	}
	
	@Test
	void filterProductTestByNameAndCategory() {
		List<ProductDto> optionalProductList = productService.find("%Cam%", 4, null);
		Assertions.assertFalse(optionalProductList.isEmpty());
	}
	
	@Test
	void filterProductTestByNameAndCategoryNoResults() {
		List<ProductDto> optionalProductList = productService.find("Ch%", 4, null);
		Assertions.assertTrue(optionalProductList.isEmpty());
	}
}
