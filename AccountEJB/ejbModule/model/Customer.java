package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="passport_number")
	private String passportNumber;

	private String password;

	private String phone;

	private String username;

	//bi-directional many-to-many association to Account
	@ManyToMany
	@JoinTable(
		name="Customer_has_Account"
		, joinColumns={
			@JoinColumn(name="Customer_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Account_id")
			}
		)
	private List<Account> accounts;

	//bi-directional many-to-many association to Address
	@ManyToMany
	@JoinTable(
		name="Customer_has_Address"
		, joinColumns={
			@JoinColumn(name="Customer_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Address_id")
			}
		)
	private List<Address> addresses;

	//bi-directional many-to-one association to ExternalPayee
	@OneToMany(mappedBy="customer")
	private List<ExternalPayee> externalPayees;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassportNumber() {
		return this.passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<ExternalPayee> getExternalPayees() {
		return this.externalPayees;
	}

	public void setExternalPayees(List<ExternalPayee> externalPayees) {
		this.externalPayees = externalPayees;
	}

	public ExternalPayee addExternalPayee(ExternalPayee externalPayee) {
		getExternalPayees().add(externalPayee);
		externalPayee.setCustomer(this);

		return externalPayee;
	}

	public ExternalPayee removeExternalPayee(ExternalPayee externalPayee) {
		getExternalPayees().remove(externalPayee);
		externalPayee.setCustomer(null);

		return externalPayee;
	}

}