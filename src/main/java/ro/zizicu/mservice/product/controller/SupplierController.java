package ro.zizicu.mservice.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.SupplierService;
import ro.zizicu.nwbase.controller.NamedEntityController;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController 
	extends NamedEntityController<Supplier, Integer> {

	public SupplierController(SupplierService supplierService) {
		super(supplierService);
	}


}
