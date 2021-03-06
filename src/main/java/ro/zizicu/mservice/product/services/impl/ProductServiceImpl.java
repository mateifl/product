package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.nwbase.exceptions.EntityNotFoundException;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
public class ProductServiceImpl extends NamedServiceImpl<Product, Integer> implements ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Override
	@Transactional
	public Product create(Product product, Category category, Supplier supplier) {
		product.setCategory(category);
		product.setSupplier(supplier);
		Product saved = repository.save(product);
		return saved;
	}

	@Override
	@Transactional
	public void discontinueProduct(Product product) {
		product.setDiscontinued("y");
		repository.save(product);
	}

	@Override
	@Transactional
	/** Executes a full or partial update on the product, after loading the product from the
	 * database.
	 */
	public Product update(Product p) {
		Product fromDatabase = repository.findById(p.getId()).orElse(null);
		if(fromDatabase == null)
			throw new EntityNotFoundException();
		if(p.getCategory() != null)
			fromDatabase.setCategory(p.getCategory());
		if(p.getQuantityPerUnit() != null)
			fromDatabase.setQuantityPerUnit(p.getQuantityPerUnit());
		if(p.getDiscontinued() != null)
			fromDatabase.setDiscontinued(p.getDiscontinued());
		if(p.getUnitsInStock() != null)
			fromDatabase.setUnitsInStock(p.getUnitsInStock());
		if(p.getUnitsOnOrder() != null)
			fromDatabase.setUnitsOnOrder(p.getUnitsOnOrder());
		Product saved = repository.save(fromDatabase);
		return saved;
	}
	
}
