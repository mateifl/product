package ro.zizicu.mservice.product.data;

import java.util.List;
import java.util.Optional;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;

public interface ProductRepositoryCustom {
	List<Product> find(String name, Optional<Category> category, Optional<Supplier> supplier);
}
