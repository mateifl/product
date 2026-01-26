package ro.zizicu.mservice.product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier implements NamedIdentityOwner<Integer> {
	
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
