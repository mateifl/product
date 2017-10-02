package ro.zizicu.mservice.product.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.zizicu.mservice.product.entities.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
	
	
	List<Supplier> findByCompanyName(String companyName);
}
