package ro.zizicu.mservice.product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "products")
@Data
public class Product implements NamedIdentityOwner<Integer> {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer id;
	@Column(name = "product_name")
	@NotBlank
	private String name;
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "unit_price")
	private Double unitPrice;
	@Column(name = "units_in_stock")
	private Integer unitsInStock;
	@Column(name = "units_on_order")
	private Integer unitsOnOrder;
	@Column(name = "reorder_level")
	private Integer reorderLevel;
	@Column(name = "discontinued")
	private Boolean discontinued;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id")
	@JsonIgnore
	private Supplier supplier;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	@JsonIgnore
	private Category category;

	@Override
	public String getEntityName() {
		return "Product";
	}
}
