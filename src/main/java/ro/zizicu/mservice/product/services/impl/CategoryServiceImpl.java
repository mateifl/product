package ro.zizicu.mservice.product.services.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.exceptions.EntityNotFoundException;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.impl.CrudServiceImpl;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<CrudRepository<Category, Integer>, Category, Integer>
	implements CategoryService {
	
	@Override
	@Transactional
	public Category update(Category category) {
		Category fromDatabase = repository.findById(category.getId()).orElse(null);
		if(fromDatabase == null)
			throw new EntityNotFoundException();
		if(category.getName() != null)
			fromDatabase.setName(category.getName());
		if(category.getDescription() != null)
			fromDatabase.setDescription(category.getDescription());
		if(category.getPicture() != null)
			fromDatabase.setPicture(category.getPicture());
		fromDatabase = repository.save(fromDatabase);
		return fromDatabase;
	}

	@Override
	public Category loadByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
