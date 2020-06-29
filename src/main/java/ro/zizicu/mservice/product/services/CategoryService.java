package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;

public interface CategoryService {
	Category create(Category category);
	Category update(Category category);
	Category load(Integer id);
	Category loadByName(String name);
	void delete(Category category);
	Iterable<Category> getAll();
}
