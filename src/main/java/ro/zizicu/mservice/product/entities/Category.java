package ro.zizicu.mservice.product.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category implements NamedIdentityOwner<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
	@SequenceGenerator(
			name = "category_seq",
			sequenceName = "sq_categories",
			allocationSize = 1
	)
	@Column(name = "category_id")
	private Integer id;
	@Column(name = "category_name")
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
