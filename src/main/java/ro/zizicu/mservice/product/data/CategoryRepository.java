package ro.zizicu.mservice.product.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.zizicu.mservice.product.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	List<Category> findByCategoryName(String categoryName);
}
