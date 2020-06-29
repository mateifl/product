package ro.zizicu.mservice.product.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.exceptions.EntityNotFoundException;
import ro.zizicu.mservice.product.services.CategoryService;

@Service
public class CategoryServiceImpl extends AbstractService<Category, CrudRepository<Category,Integer>> implements CategoryService {

	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Override
	@Transactional
	public Category update(Category category) {
		Category fromDatabase = categoryRepository.findById(category.getCategoryId()).orElse(null);
		if(fromDatabase == null)
			throw new EntityNotFoundException();
		if(category.getCategoryName() != null)
			fromDatabase.setCategoryName(category.getCategoryName());
		if(category.getDescription() != null)
			fromDatabase.setDescription(category.getDescription());
		if(category.getPicture() != null)
			fromDatabase.setPicture(category.getPicture());
		fromDatabase = categoryRepository.save(fromDatabase);
		return fromDatabase;
	}

	@Override
	public Category loadByName(String name) {
		List<Category> categories = categoryRepository.findByCategoryName(name);
		return null;
	}

}
