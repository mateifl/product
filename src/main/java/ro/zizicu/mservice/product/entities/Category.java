package ro.zizicu.mservice.product.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity 
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
	@Size(min=1, max=120, message = "picture path must be between 1 and 50 characters long")
	private String picture;

	@Override
	public String getEntityName() {
		return "Category";
	}
}
