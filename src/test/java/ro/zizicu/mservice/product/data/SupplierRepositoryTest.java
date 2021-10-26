package ro.zizicu.mservice.product.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.zizicu.mservice.product.entities.Supplier;

@SpringBootTest
public class SupplierRepositoryTest {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Test
	public void testLoadSupplier() {
		Supplier s = supplierRepository.findById(2).get();
		Assertions.assertNotNull(s, "test load supplier");
	}
	
}
