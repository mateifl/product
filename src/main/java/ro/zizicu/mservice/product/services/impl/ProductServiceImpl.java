package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.exceptions.ProductNotFoundException;
import ro.zizicu.mservice.product.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Autowired 
	private SupplierRepository supplierRepository;
	
	@Override
	@Transactional
	public Product createProduct(Product product, Category category, Supplier supplier) {
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
	public void deleteProduct(Product p) {
		repository.delete(p);
	}

	@Override
	public Product loadProduct(Integer productId) {
		Product product = repository.findById(productId).get();
		return product;
	}

	@Override
	public void createSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Supplier loadSupplier(Integer id) {
		Supplier supplier = supplierRepository.findById(id).get();
		return supplier;
	}

	@Override
	public void deleteSupplier(Supplier supplier) {
		supplierRepository.delete(supplier);
		
	}

	@Override
	public Iterable<Product> getProducts() {
		return repository.findAll();
	}

	@Override
	@Transactional
	/** Executes a full or partial update on the product, after loading the product from the
	 * database.
	 */
	public Product updateProduct(Product p) {
		Product fromDatabase = repository.findById(p.getProductId()).orElse(null);
		if(fromDatabase == null)
			throw new ProductNotFoundException();
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
