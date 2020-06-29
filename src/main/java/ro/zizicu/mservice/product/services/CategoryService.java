package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;

public interface CategoryService {
	Category createCategory(Category category);
	Category updateCategory(Category category);
	Category loadCategory(Integer id);
	Category loadByName(String name);
	void deleteCategory(Category category);
	Iterable<Category> getCategories();
}
