package ro.zizicu.mservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Supplier;
// TODO do I need this? 
@RestController
public class SupplierController {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@RequestMapping(value = "/suppliers", method = RequestMethod.GET)
	public Iterable<Supplier> loadAll() {
		return supplierRepository.findAll();
	}
	
}
