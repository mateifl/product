package ro.zizicu.mservice.product.dto;

import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.service.converter.DtoConverter;


public class ProductDtoConverter implements DtoConverter<Product, ProductDto, Integer> {
    @Override
    public ProductDto convertToDTO(Product e) {
        ProductDto productDto = new ProductDto();
        productDto.fromEntity(e);
        return productDto;
    }

    @Override
    public Product convertToEntity(ProductDto d) {
        return null;
    }
}
