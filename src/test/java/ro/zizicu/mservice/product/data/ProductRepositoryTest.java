package ro.zizicu.mservice.product.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.zizicu.mservice.product.entities.Product;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testLoad() {
		Product product = productRepository.findById(3).get();
		Assertions.assertTrue(product.getId() == 3);
	}

}
