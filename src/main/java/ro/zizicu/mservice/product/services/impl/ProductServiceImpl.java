package ro.zizicu.mservice.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.nwbase.exceptions.EntityNotFoundException;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl extends NamedServiceImpl<Product, Integer> implements ProductService {

	private final ProductRepository repository;
	private final CategoryRepository categoryRepository; 
	private final SupplierRepository supplierRepository;
	@Override
	@Transactional              
	public Product create(Product product, Category category, Supplier supplier) {
		product.setCategory(category);
		product.setSupplier(supplier);
		return repository.save(product);
	}

	@Override
	@Transactional
	public void discontinueProduct(Product product) {
		product.setDiscontinued(true);
		repository.save(product);
	}

	/** Executes a full or partial update on the product, after loading the product from the
	 * database.
	 */
	@Override
	@Transactional
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
		return repository.save(fromDatabase);
	}
	
	@Override
	public Optional<List<Product>> find(String name, Integer categoryId, Integer supplierId) {
		Optional<Category> category = categoryId == null ? Optional.empty() : categoryRepository.findById(categoryId);
		Optional<Supplier> supplier = supplierId == null ? Optional.empty() : supplierRepository.findById(supplierId);
		return repository.find(name, category, supplier);
	}
}
