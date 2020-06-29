package ro.zizicu.mservice.product.services;

import ro.zizicu.mservice.product.entities.Supplier;

public interface SupplierService {
	Supplier create(Supplier Supplier);
	Supplier update(Supplier Supplier);
	Supplier load(Integer id);
	Supplier loadByName(String name);
	void delete(Supplier Supplier);
	Iterable<Supplier> getAll();
}
