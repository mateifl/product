package ro.zizicu.mservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.entities.Category;

/**
 * Category Spring MVC controller.
 * @author matei.florescu
 */

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category load(@PathVariable Integer id)
	{
		return categoryRepository.findById(id).get();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Category> loadAll()
	{
		return categoryRepository.findAll();
	}
}
