package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Supplier;

public interface ProductService {
	void createProduct(Product product, Category category, Supplier supplier);
	void discontinueProduct(Product product);
	void deleteProduct(Product p);
	
	/** TODO do I need this method to take the id as the parameter */
	Product loadProduct(Product p);
	void createCategory(Category category);
	
}
