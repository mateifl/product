package ro.zizicu.mservice.product.services.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.data.NamedEntityRepository;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
public class CategoryServiceImpl extends NamedServiceImpl<Category, Integer>
	implements CategoryService {

	public CategoryServiceImpl(CrudRepository<Category, Integer> repository, NamedEntityRepository<Category, Integer> namedRepository) {
		super(repository, namedRepository);
	}

	@Override
	@Transactional
	public Category update(Category category) {
		Category fromDatabase = getRepository().findById(category.getId()).orElseThrow();
		if(category.getName() != null)
			fromDatabase.setName(category.getName());
		if(category.getDescription() != null)
			fromDatabase.setDescription(category.getDescription());
		fromDatabase = getRepository().save(fromDatabase);
		return fromDatabase;
	}

}
