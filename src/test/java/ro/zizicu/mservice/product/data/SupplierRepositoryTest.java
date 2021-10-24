package ro.zizicu.mservice.product.data;

import static org.junit.Assert.assertTrue;

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
		assertTrue("test load supplier", s != null);
	}
	
}
