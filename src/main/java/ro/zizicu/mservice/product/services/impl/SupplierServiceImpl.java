package ro.zizicu.mservice.product.services.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.SupplierService;

@Service
public class SupplierServiceImpl extends AbstractService<Supplier, CrudRepository<Supplier,Integer>> implements SupplierService {

	@Override
	public Supplier update(Supplier Supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier loadByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
