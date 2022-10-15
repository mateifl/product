package ro.zizicu.mservice.product.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "Products")
@Data
public class Product implements NamedIdentityOwner<Integer> {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productid")
	private Integer id;
	@Column(name = "productname")
	@NotBlank
	private String name;
	@Column(name = "quantityperunit")
	private String quantityPerUnit;
	@Column(name = "unitprice")
	private Double unitPrice;
	@Column(name = "unitsinstock")
	private Integer unitsInStock;
	@Column(name = "unitsonorder")
	private Integer unitsOnOrder;
	@Column(name = "reorderlevel")
	private Integer reorderLevel;
	@Column(name = "discontinued")
	private Boolean discontinued;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplierid")
	@JsonIgnore
	private Supplier supplier;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryid")
	@JsonIgnore
	private Category category;

	@Override
	public String getEntityName() {
		return "Product";
	}
}
