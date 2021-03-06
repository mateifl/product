package ro.zizicu.mservice.product.data;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.zizicu.mservice.product.entities.Category;

@RunWith(SpringJUnit4ClassRunner.class)
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
		assertTrue("save category", c.getId() != null);
		repository.delete(c);
		
	}
	
	@Test
	public void testLoadCategory() {
		Category c = repository.findById(1).get();
		assertTrue("load category", c.getId() == 1);
	}
}
