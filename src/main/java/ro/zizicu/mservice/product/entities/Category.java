package ro.zizicu.mservice.product.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity 
@Table(name = "categories")
public class Category implements NamedIdentityOwner<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryid")
	private Integer id;
	@Column(name = "categoryname")
	@NotEmpty(message="category name is mandatory")
	@Size(min=1, max=15, message = "category name must be between 1 and 15 characters long")
	private String name;
	@NotEmpty(message="description is mandatory")
	private String description;
	@NotEmpty(message="picture path is mandatory")
	@Size(min=1, max=50, message = "picture path must be between 1 and 50 characters long")
	private String picture;

	public Category() {}

	public Category(Integer categoryId, String categoryName, String description, String picture) {
		super();
		this.id = categoryId;
		this.name = categoryName;
		this.description = description;
		this.picture = picture;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer categoryId) {
		this.id = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String categoryName) {
		this.name = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
