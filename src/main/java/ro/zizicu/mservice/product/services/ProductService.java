package ro.zizicu.mservice.product.services;

import java.util.List;

import ro.zizicu.mservice.product.data.ProductRepository;
import ro.zizicu.mservice.product.dto.ProductDto;
import ro.zizicu.mservice.product.dto.ProductDtoConverter;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.service.LoaderService;
import ro.zizicu.nwbase.service.NamedService;
import ro.zizicu.nwbase.service.impl.DefaultLoaderService;

public interface ProductService extends LoaderService<ProductRepository,
													  ProductDtoConverter,
													  Product,
													  ProductDto,
													  Integer> {

	/** Execute a full or partial update on the product */
	ProductDto update(ProductDto p);

	List<ProductDto> find(String name, Integer categoryId, Integer supplierId);

	ProductDto create(ProductDto productDto);
}
