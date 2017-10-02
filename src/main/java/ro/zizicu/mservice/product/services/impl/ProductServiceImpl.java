package ro.zizicu.mservice.product.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductRepository repository;
	private CategoryRepository categoryRepository;
	private SupplierRepository supplierRepository;
	
	@Override
	@Transactional
	public void createProduct(Product product, Category category, Supplier supplier) {
		List<Category> categories = categoryRepository.findByCategoryName(category.getCategoryName());
		if(categories.isEmpty())
		{
			
		}
		repository.save(product);
	}

	@Override
	@Transactional
	public void discontinueProduct(Product product) {
		product.setDiscontinued("y");
		repository.save(product);
	}

	@Override
	public void deleteProduct(Product p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product loadProduct(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub

	}

}
