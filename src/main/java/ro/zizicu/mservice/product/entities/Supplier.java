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
@Table(name = "Supplier")
@Getter
@Setter
public class Supplier implements NamedIdentityOwner<Integer> {
	
	@Id
	@Column(name="supplierid")
	private Integer id;
	@Column(name="companyname")
	@NotBlank
	private String name;
	@Column(name="contactname")
	private String contactName;
	private String address;
	private String city;
	@Column(name="postalcode")
	private String postalCode;
	private String country;
	private String phone;

	@Override
	public String getEntityName() {
		return "Supplier";
	}

}
