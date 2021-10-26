package ro.zizicu.mservice.product.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.zizicu.mservice.product.entities.Category;


@SpringBootTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void testSaveCategory() {
		Category c = new Category();
		c.setName("Test1");
		c.setDescription("Category description");
		c.setPicture("Picture test1");
		repository.save(c);
		Assertions.assertNotNull(c.getId(), "save category");
		repository.delete(c);
		
	}
	
	@Test
	public void testLoadCategory() {
		Category c = repository.findById(1).get();
		Assertions.assertEquals(1, (int) c.getId(), "load category");
	}
}
