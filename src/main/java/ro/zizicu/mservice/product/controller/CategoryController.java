package ro.zizicu.mservice.product.controller;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.exceptions.EntityNotFoundException;
import ro.zizicu.mservice.product.services.CategoryService;

/**
 * Category Spring MVC controller.
 * @author matei.florescu
 */

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> load(@PathVariable @Min(1) Integer id)
	{
		try {
			return ResponseEntity.ok(categoryService.loadCategory(id));
		}
		catch(EntityNotFoundException e) {
			String errorMessage = "category not found, id:" + id;
			logger.error(errorMessage);
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
	@GetMapping(value = "/")
	public Iterable<Category> loadAll()
	{
		return categoryService.getCategories();
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<?> create(@RequestBody Category category) {
		try {
			Category c = categoryService.createCategory(category);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Location", "categories/" + c.getCategoryId());
			return ResponseEntity.ok().headers(responseHeaders).body(c);
		}
		catch (Exception e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
	@PatchMapping(value = "/{id}") 
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Category category) {
		try {
			return ResponseEntity.ok(categoryService.updateCategory(category));
		}
		catch (Exception e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
	@DeleteMapping(value="/")
	public ResponseEntity<?> delete(@RequestBody Category category) {
		try {
			categoryService.deleteCategory(category);
			return ResponseEntity.ok("deleted");
		}
		catch (Exception e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
}
