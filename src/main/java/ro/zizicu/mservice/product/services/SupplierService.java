package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.service.CrudService;
import ro.zizicu.nwbase.service.NamedService;

public interface SupplierService extends CrudService<Supplier, Integer>, NamedService<Supplier, Integer> {

}
