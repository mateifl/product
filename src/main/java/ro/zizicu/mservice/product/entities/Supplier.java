package ro.zizicu.mservice.product.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier implements NamedIdentityOwner<Integer> {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq")
	@SequenceGenerator(
			name = "supplier_seq",
			sequenceName = "sq_suppliers",
			allocationSize = 1
	)
	@Id
	@Column(name="supplier_id")
	private Integer id;
	@Column(name="company_name")
	@NotBlank
	private String name;
	@Column(name="contact_name")
	private String contactName;
	@NotBlank
	private String address;
	@NotBlank
	private String city;
	@Column(name="postal_code")
	private String postalCode;
	private String country;
	private String phone;

	@Override
	public String getEntityName() {
		return "Supplier";
	}

}
