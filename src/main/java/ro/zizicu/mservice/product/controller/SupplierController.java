package ro.zizicu.mservice.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.controller.NamedEntityController;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController 
	extends NamedEntityController<Supplier, Integer> {

	@Override
	protected String getLocation() {
		return "suppliers";
	}

}
