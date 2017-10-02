package ro.zizicu.mservice.product.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	/**
	 *  
	 *  */

	List<Product> findByProductName(String productName);
	
	/** */
	List<Product> findByCategory(Category category);
	
	/** */
	List<Product> findBySupplier(Supplier supplier);
}
