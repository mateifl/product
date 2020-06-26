package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;

public interface ProductService {
	void createProduct(Product product, Category category, Supplier supplier);
	void discontinueProduct(Product product);
	void deleteProduct(Product p);
	
	Product loadProduct(Integer id);

	void createSupplier(Supplier supplier);
	Supplier loadSupplier(Integer id);
	void deleteSupplier(Supplier supplier);
	Iterable<Product> getProducts();
}
