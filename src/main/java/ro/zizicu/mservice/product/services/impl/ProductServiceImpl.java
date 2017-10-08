package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;
	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired 
	private SupplierRepository supplierRepository;
	
	@Override
	@Transactional
	public void createProduct(Product product, Category category, Supplier supplier) {
		product.setCategory(category);
		product.setSupplier(supplier);
		repository.save(product);
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
		Product product = repository.findOne(productId);
		return product;
	}

	@Override
	@Transactional
	public void createCategory(Category category) {
		categoryRepository.save(category);

	}

	@Override
	public void createSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category loadCategory(Integer id) {
		Category category = categoryRepository.findOne(id);
		return category;
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	public Supplier loadSupplier(Integer id) {
		Supplier supplier = supplierRepository.findOne(id);
		return supplier;
	}

	@Override
	public void deleteSupplier(Supplier supplier) {
		supplierRepository.delete(supplier);
		
	}

	
	
}
