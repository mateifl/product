package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.SupplierService;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
public class SupplierServiceImpl 
	extends NamedServiceImpl<Supplier, Integer>
	implements SupplierService 
{
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		super(supplierRepository);
	}

	@Override
	protected Supplier transform(Supplier e) {
		Supplier transformed = new Supplier();
		BeanUtils.copyProperties(e, transformed);
		return transformed;
	}
}
