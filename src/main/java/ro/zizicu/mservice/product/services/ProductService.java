package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.service.CrudService;
import ro.zizicu.nwbase.service.NamedService;

public interface ProductService extends NamedService<Product, Integer>, CrudService<Product, Integer> {
	Product create(Product product, Category category, Supplier supplier);
	void discontinueProduct(Product product);
	/** Execute a full or partial update on the product */
	Product update(Product p);

}
