package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;

public interface CategoryService {
	void createCategory(Category category);
	Category loadCategory(Integer id);
	void deleteCategory(Category category);
	Iterable<Category> getCategories();
}
