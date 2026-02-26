package ro.zizicu.mservice.product.services.impl;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;

import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.nwbase.service.impl.NamedServiceImpl;

@Service
@Slf4j
public class ProductServiceImpl  extends NamedServiceImpl<Product, Integer>
		implements ProductService {

	private final CategoryRepository categoryRepository; 
	private final SupplierRepository supplierRepository;

	public ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepository,
			SupplierRepository supplierRepository) {
		super(repository);
		this.categoryRepository = categoryRepository;
		this.supplierRepository = supplierRepository;
	}

	/** Executes a full or partial update on the product, after loading the product from the
	 * database.
	 */
	@Override
	@Transactional
	public Product update(Product p) {
		Product fromDatabase = getRepository().findById(p.getId()).orElseThrow();
		if(p.getCategory() != null && !p.getCategory().getId().equals(fromDatabase.getCategory().getId())) {
			Category category = categoryRepository.findById(p.getCategory().getId()).orElseThrow();
			fromDatabase.setCategory(category);
		}

		if(p.getSupplier() != null && !p.getSupplier().getId().equals(fromDatabase.getSupplier().getId())) {
			Supplier supplier = supplierRepository.findById(p.getSupplier().getId()).orElseThrow();
			fromDatabase.setSupplier(supplier);
		}

		fromDatabase.setName(p.getName());

		if(p.getQuantityPerUnit() != null)
			fromDatabase.setQuantityPerUnit(p.getQuantityPerUnit());
		if(p.getDiscontinued() != null)
			fromDatabase.setDiscontinued(p.getDiscontinued());
		if(p.getUnitsInStock() != null)
			fromDatabase.setUnitsInStock(p.getUnitsInStock());
		if(p.getUnitsOnOrder() != null)
			fromDatabase.setUnitsOnOrder(p.getUnitsOnOrder());
		if(p.getDiscontinued() != null)
			fromDatabase.setDiscontinued(p.getDiscontinued());

		return transform(getRepository().save(fromDatabase));
	}

	@Override
	public List<Product> find(String name, Integer categoryId, Integer supplierId) {
		log.debug("filtering products");
		Optional<Category> category = categoryId == null ? Optional.empty() : categoryRepository.findById(categoryId);
		Optional<Supplier> supplier = supplierId == null ? Optional.empty() : supplierRepository.findById(supplierId);
		return ((ProductRepository)getRepository()).find(name, category, supplier).stream().map(this::transform).toList();
	}

	@Override
	public Product create(Product product) {
		Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
		Optional<Supplier> supplierOptional = supplierRepository.findById(product.getSupplier().getId());
		product.setCategory(categoryOptional.orElseThrow());
		product.setSupplier(supplierOptional.orElseThrow());
		Product persistedProduct = transform(getRepository().save(product));
		return persistedProduct;
	}

	@Override
	protected Product transform(Product fromProduct) {
		log.trace("transforming product {}", fromProduct.getId());
		Product toProduct = new Product();
		toProduct.setName(fromProduct.getName());
		Category category = new  Category();
		category.setId(fromProduct.getCategory().getId());
		Supplier supplier = new  Supplier();
		supplier.setId(fromProduct.getSupplier().getId());
		toProduct.setCategory(category);
		toProduct.setSupplier(supplier);
		toProduct.setQuantityPerUnit(fromProduct.getQuantityPerUnit());
		toProduct.setDiscontinued(fromProduct.getDiscontinued());
		toProduct.setUnitsInStock(fromProduct.getUnitsInStock());
		toProduct.setUnitsOnOrder(fromProduct.getUnitsOnOrder());
		toProduct.setId(fromProduct.getId());
		return toProduct;
	}

}

