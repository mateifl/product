package ro.zizicu.mservice.product.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "Category")
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

	@Override
	public String getEntityName() {
		return "Category";
	}
}
