package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.exceptions.EntityNotFoundException;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
public class CategoryServiceImpl extends NamedServiceImpl<Category, Integer>
	implements CategoryService {
	
	@Override
	@Transactional
	public Category update(Category category) {
		Category fromDatabase = repository.findById(category.getId()).orElseThrow();
		if(category.getName() != null)
			fromDatabase.setName(category.getName());
		if(category.getDescription() != null)
			fromDatabase.setDescription(category.getDescription());
		fromDatabase = repository.save(fromDatabase);
		return fromDatabase;
	}

}
