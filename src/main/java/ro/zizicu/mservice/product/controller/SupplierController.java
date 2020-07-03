package ro.zizicu.mservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.SupplierService;
import ro.zizicu.nwbase.controller.BasicOperationsController;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController 
	extends BasicOperationsController<Supplier, Integer, SupplierService> {

	@Autowired
	private SupplierService supplierService;

}
