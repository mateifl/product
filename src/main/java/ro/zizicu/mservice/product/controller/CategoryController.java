package ro.zizicu.mservice.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.services.CategoryService;
import ro.zizicu.nwbase.controller.BasicOperationsController;

/**
 * Category Spring MVC controller.
 * @author matei.florescu
 */

@RestController
@RequestMapping(value = "categories")
public class CategoryController 
	extends BasicOperationsController<Category, Integer, CategoryService> {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@PatchMapping(value = "/{id}") 
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Category category) {
		logger.debug("update category id: " + id);
		category.setId(id);
		try {
			return ResponseEntity.ok(service.update(category));
		}
		catch (Exception e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}

}
