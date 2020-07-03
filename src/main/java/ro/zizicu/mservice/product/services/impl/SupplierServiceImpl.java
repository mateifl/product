package ro.zizicu.mservice.product.services.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.SupplierService;
import ro.zizicu.nwbase.impl.CrudServiceImpl;

@Service
public class SupplierServiceImpl 
	extends CrudServiceImpl<CrudRepository<Supplier,Integer>, Supplier, Integer> 
	implements SupplierService {

	@Override
	public Supplier update(Supplier Supplier) {
		// TODO Auto-generated method stub
		return null;
	}



}
