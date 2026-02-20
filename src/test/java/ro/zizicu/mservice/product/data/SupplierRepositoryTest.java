package ro.zizicu.mservice.product.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.testcontainers.junit.jupiter.Testcontainers;
import ro.zizicu.mservice.product.BaseIntegrationTest;
import ro.zizicu.mservice.product.entities.Supplier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SupplierRepositoryTest extends BaseIntegrationTest {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Test
	public void testLoadSupplier() {
		Supplier s = supplierRepository.findById(2).get();
		Assertions.assertNotNull(s, "test load supplier");
	}
	
}
