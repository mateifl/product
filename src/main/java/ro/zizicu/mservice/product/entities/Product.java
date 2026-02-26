package ro.zizicu.mservice.product.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "products")
@Data
public class Product implements NamedIdentityOwner<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@SequenceGenerator(
			name = "product_seq",
			sequenceName = "sq_products",  // actual sequence name in DB
			allocationSize = 1,
			initialValue = 78
	)
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
	private Integer discontinued;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id")
	private Supplier supplier;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;

	@Override
	public String getEntityName() {
		return "Product";
	}
}
