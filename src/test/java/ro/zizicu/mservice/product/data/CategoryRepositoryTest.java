package ro.zizicu.mservice.product.data;

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
		c.setCategoryName("Test1");
		c.setDescription("Category description");
		c.setPicture("Picture test1");
		repository.save(c);
	}
	
}
