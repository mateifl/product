package ro.zizicu.mservice.product.services;

import java.util.List;

import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.service.NamedService;

public interface ProductService extends NamedService<Product, Integer> {

	/** Execute a full or partial update on the product */
	Product update(Product p);

	List<Product> find(String name, Integer categoryId, Integer supplierId);

	Product create(Product productDto);
}
