package ro.zizicu.mservice.product.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ro.zizicu.mservice.product.entities.Product;
import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Data
public class ProductDto implements NamedIdentityOwner<Integer> {

    private Integer id;
    private String name;
    private String quantityPerUnit;

    private Float unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private Integer discontinued;

    private Integer categoryId;
    private Integer supplierId;

    @Override
    public String getEntityName() {
        return "ProductDto";
    }

    public ProductDto fromEntity(Product product) {
        BeanUtils.copyProperties(product, this);
        this.categoryId = product.getCategory().getId();
        this.supplierId = product.getSupplier().getId();
        return this;
    }

}
