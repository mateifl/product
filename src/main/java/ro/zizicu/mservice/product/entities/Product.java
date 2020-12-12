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

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "Products")
public class Product implements NamedIdentityOwner<Integer> {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productid")
	private Integer id;
	@Column(name = "productname")
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
	private String discontinued;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplierid")
	@JsonIgnore
	private Supplier supplier;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryid")
	@JsonIgnore
	private Category category;
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public void setId(Integer productId) {
		this.id = productId;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String productName) {
		this.name = productName;
	}
	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}
	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public Integer getUnitsOnOrder() {
		return unitsOnOrder;
	}
	public void setUnitsOnOrder(Integer unitsInOrder) {
		this.unitsOnOrder = unitsInOrder;
	}
	public Integer getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String getEntityName() {
		return "Product";
	}
}
