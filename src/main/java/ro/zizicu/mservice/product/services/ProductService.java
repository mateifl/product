package ro.zizicu.mservice.product.services;

import java.util.List;

import ro.zizicu.mservice.product.dto.ProductDto;
import ro.zizicu.nwbase.service.DTOService;

public interface ProductService extends DTOService<ProductDto, Integer> {

	/** Execute a full or partial update on the product */
	ProductDto update(ProductDto p);

	List<ProductDto> find(String name, Integer categoryId, Integer supplierId);

	ProductDto create(ProductDto productDto);
}
