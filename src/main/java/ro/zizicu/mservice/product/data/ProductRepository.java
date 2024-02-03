package ro.zizicu.mservice.product.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.data.NamedEntityRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>,
										   NamedEntityRepository<Product, Integer>, ProductRepositoryCustom {
	List<Product> findByCategory(Category category);
	
	List<Product> findBySupplier(Supplier supplier);

}
