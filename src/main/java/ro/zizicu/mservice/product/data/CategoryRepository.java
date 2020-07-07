package ro.zizicu.mservice.product.data;

import org.springframework.data.repository.CrudRepository;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.nwbase.data.NamedEntityRepository;

public interface CategoryRepository 
		extends CrudRepository<Category, Integer>, NamedEntityRepository<Category, Integer> {
}
