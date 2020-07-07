package ro.zizicu.mservice.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.controller.NamedEntityController;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController 
	extends NamedEntityController<Supplier, Integer> {

	private static Logger logger = LoggerFactory.getLogger(SupplierController.class);
	


}
