package ro.zizicu.mservice.product.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ro.zizicu.mservice.product.data.CategoryRepository;
import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.data.SupplierRepository;
import ro.zizicu.mservice.product.dto.ProductDto;
import ro.zizicu.mservice.product.dto.ProductDtoConverter;
import ro.zizicu.mservice.product.entities.Category;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.mservice.product.entities.Supplier;
import ro.zizicu.mservice.product.services.ProductService;
import ro.zizicu.nwbase.service.impl.DefaultLoaderService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl  extends DefaultLoaderService<ProductRepository, ProductDtoConverter, Product, ProductDto, Integer>
		implements ProductService {

	private final ProductRepository repository;
	private final CategoryRepository categoryRepository; 
	private final SupplierRepository supplierRepository;

	/** Executes a full or partial update on the product, after loading the product from the
	 * database.
	 */
	@Override
	@Transactional
	public ProductDto update(ProductDto p) {
		Product fromDatabase = repository.findById(p.getId()).orElseThrow();
		if(p.getCategoryId() != null && !p.getCategoryId().equals(fromDatabase.getCategory().getId())) {
			Category category = categoryRepository.findById(p.getCategoryId()).orElseThrow();
			fromDatabase.setCategory(category);
		}

		if(p.getSupplierId() != null && !p.getSupplierId().equals(fromDatabase.getSupplier().getId())) {
			Supplier supplier = supplierRepository.findById(p.getSupplierId()).orElseThrow();
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

		return  p.fromEntity(repository.save(fromDatabase));
	}

	@Override
	public List<ProductDto> find(String name, Integer categoryId, Integer supplierId) {
		Optional<Category> category = categoryId == null ? Optional.empty() : categoryRepository.findById(categoryId);
		Optional<Supplier> supplier = supplierId == null ? Optional.empty() : supplierRepository.findById(supplierId);
		return repository.find(name, category, supplier).stream().map( product -> {
			ProductDto productDto = new ProductDto().fromEntity(product);
			return productDto;
		}).collect(Collectors.toList());
	}

	@Override
	public ProductDto create(ProductDto productDto) {
		Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
		Optional<Supplier> supplierOptional = supplierRepository.findById(productDto.getSupplierId());
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		product.setCategory(categoryOptional.orElseThrow());
		product.setSupplier(supplierOptional.orElseThrow());
		Product persistedProduct = repository.save(product);
		BeanUtils.copyProperties(persistedProduct, productDto);
		return productDto;
	}

}

