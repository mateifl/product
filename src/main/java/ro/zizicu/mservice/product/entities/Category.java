package ro.zizicu.mservice.product.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryid")
	private Integer categoryId;
	@Column(name = "categoryname")
	private String categoryName;
	private String description;
	private String picture;

	public Category() {}

	public Category(Integer categoryId, String categoryName, String description, String picture) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
		this.picture = picture;
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
