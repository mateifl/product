package ro.zizicu.mservice.product.data;

import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.nwbase.data.NamedEntityRepository;

public interface SupplierRepository 
		extends NamedEntityRepository<Supplier, Integer> {
}
