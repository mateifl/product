package ro.zizicu.mservice.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.controller.NamedEntityController;

@RestController
@RequestMapping(value = "categories")
public class CategoryController 
	extends NamedEntityController<Category, Integer> {

	public CategoryController(CategoryService categoryService) {
		super(categoryService);
	}

	@Override
	protected String getLocation() {
		return "categories";
	}
}
