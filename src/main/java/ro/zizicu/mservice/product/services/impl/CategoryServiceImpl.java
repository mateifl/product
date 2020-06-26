package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Override
	public Category loadCategory(Integer id) {
		Category category = categoryRepository.findById(id).get();
		return category;
	}

	@Override
	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}
	
	@Override
	@Transactional
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	@Transactional
	public void createCategory(Category category) {
		categoryRepository.save(category);

	}

}
