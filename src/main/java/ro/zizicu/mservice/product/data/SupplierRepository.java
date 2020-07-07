package ro.zizicu.mservice.product.data;

import org.springframework.data.repository.CrudRepository;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.data.NamedEntityRepository;

public interface SupplierRepository 
		extends CrudRepository<Supplier, Integer>, NamedEntityRepository<Supplier, Integer> {
}
