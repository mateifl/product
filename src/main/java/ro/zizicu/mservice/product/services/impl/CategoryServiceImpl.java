package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
public class CategoryServiceImpl extends NamedServiceImpl<Category, Integer>
	implements CategoryService {

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super(categoryRepository);
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

	@Override
	protected Category transform(Category e) {
		Category transformed = new Category();
		BeanUtils.copyProperties(e, transformed);
		return transformed;
	}
}
