package ro.zizicu.mservice.product.data;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.nwbase.data.NamedEntityRepository;

public interface CategoryRepository 
		extends NamedEntityRepository<Category, Integer> {
}
