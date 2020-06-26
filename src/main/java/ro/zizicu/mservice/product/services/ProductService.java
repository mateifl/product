package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;

public interface ProductService {
	Product createProduct(Product product, Category category, Supplier supplier);
	void discontinueProduct(Product product);
	void deleteProduct(Product p);
	/** Execute a full or partial update on the product */
	Product updateProduct(Product p);
	Product loadProduct(Integer id);

	void createSupplier(Supplier supplier);
	Supplier loadSupplier(Integer id);
	void deleteSupplier(Supplier supplier);
	Iterable<Product> getProducts();
}
