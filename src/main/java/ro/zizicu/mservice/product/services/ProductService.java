package ro.zizicu.mservice.product.services;

import java.util.List;
import java.util.Optional;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.service.NamedService;

public interface ProductService extends NamedService<Product, Integer> {
	Product create(Product product, Category category, Supplier supplier);
	void discontinueProduct(Product product);
	/** Execute a full or partial update on the product */
	Product update(Product p);
	Optional<List<Product>> find(String name, Integer categoryId, Integer supplierId);
}
