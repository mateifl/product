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
@Table(name = "Product")
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
