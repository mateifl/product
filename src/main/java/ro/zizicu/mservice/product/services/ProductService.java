package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;

public interface ProductService {
	Product create(Product product, Category category, Supplier supplier);
	void discontinueProduct(Product product);
	/** Execute a full or partial update on the product */
	Product update(Product p);

}
