package ro.zizicu.mservice.product.data;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.zizicu.mservice.product.entities.Supplier;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SupplierRepositoryTest {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Test
	public void testLoadSupplier() {
		Supplier s = supplierRepository.findOne(2);
		assertTrue("test load supplier", s != null);
	}
	
}
