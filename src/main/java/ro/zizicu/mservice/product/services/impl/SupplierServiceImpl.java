package ro.zizicu.mservice.product.services.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.SupplierService;
import ro.zizicu.nwbase.data.NamedEntityRepository;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
public class SupplierServiceImpl 
	extends NamedServiceImpl<Supplier, Integer>
	implements SupplierService 
{
	public SupplierServiceImpl(CrudRepository<Supplier, Integer> repository, NamedEntityRepository<Supplier, Integer> namedRepository) {
		super(repository, namedRepository);
	}
}
