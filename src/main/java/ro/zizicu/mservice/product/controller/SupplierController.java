package ro.zizicu.mservice.product.controller;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.exceptions.EntityNotFoundException;
import ro.zizicu.mservice.product.services.SupplierService;

@RestController
public class SupplierController {

	private static Logger logger = LoggerFactory.getLogger(SupplierController.class);
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping(value = "/suppliers")
	public Iterable<Supplier> loadAll() {
		return supplierService.getAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> load(@PathVariable @Min(1) Integer id)
	{
		try {
			return ResponseEntity.ok(supplierService.load(id));
		}
		catch(EntityNotFoundException e) {
			String errorMessage = "category not found, id:" + id;
			logger.error(errorMessage);
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
}
