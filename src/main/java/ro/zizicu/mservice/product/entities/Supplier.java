package ro.zizicu.mservice.product.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ro.zizicu.nwbase.entity.NamedIdentityOwner;

@Entity
@Table(name = "Suppliers")
public class Supplier implements NamedIdentityOwner<Integer> {
	
	@Id
	@Column(name="supplierid")
	private Integer id;
	@Column(name="companyname")
	private String name;
	@Column(name="contactname")
	private String contactName;
	@Column(name="contacttitle")
	private String contactTitle;
	private String address;
	private String city;
	private String region;
	@Column(name="postalcode")
	private String postalCode;
	private String country;
	private String phone;
	private String fax;
	@Column(name="homepage")
	private String homePage;

	public Integer getId() {
		return id;
	}
	public void setSupplierId(Integer supplierId) {
		this.id = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String companyName) {
		this.name = companyName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	@Override
	public String getEntityName() {
		return "Supplier";
	}
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
