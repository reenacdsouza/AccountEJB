package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the Customer database table.
 * 
 */
@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "passport_number")
	private String passportNumber;

	private String password;

	private String phone;

	private String username;

	// bi-directional many-to-many association to Account
	@ManyToMany
	@JoinTable(name = "Customer_has_Account", joinColumns = {
			@JoinColumn(name = "Customer_id") }, inverseJoinColumns = { @JoinColumn(name = "Account_id") })
	private Set<Account> accounts = new HashSet<Account>();

	// bi-directional many-to-many association to Address
	@ManyToMany
	@JoinTable(name = "Customer_has_Address", joinColumns = {
			@JoinColumn(name = "Customer_id") }, inverseJoinColumns = { @JoinColumn(name = "Address_id") })
	private Set<Address> addresses = new HashSet<Address>();

	// bi-directional many-to-many association to ExternalBiller
	@ManyToMany
	@JoinTable(name = "Customer_has_ExternalBiller", joinColumns = {
			@JoinColumn(name = "Customer_id") }, inverseJoinColumns = { @JoinColumn(name = "ExternalBiller_id") })
	private Set<ExternalBiller> externalBillers = new HashSet<ExternalBiller>();

	// bi-directional many-to-many association to ExternalPayee
	@ManyToMany
	@JoinTable(name = "Customer_has_ExternalPayee", joinColumns = {
			@JoinColumn(name = "Customer_id") }, inverseJoinColumns = { @JoinColumn(name = "ExternalPayee_id") })
	private Set<ExternalPayee> externalPayees = new HashSet<ExternalPayee>();

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

	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
		account.getCustomers().add(this);
	}

	public void removeAccount(Account account) {
		this.accounts.remove(account);
		account.getCustomers().remove(this);
	}

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getCustomers().add(this);
	}

	public void removeAddress(Address address) {
		this.addresses.remove(address);
		address.getCustomers().remove(this);
	}

	public Set<ExternalBiller> getExternalBillers() {
		return this.externalBillers;
	}

	public void setExternalBillers(Set<ExternalBiller> externalBillers) {
		this.externalBillers = externalBillers;
	}
	
	public void addExternalBiller(ExternalBiller externalBiller) {
		this.externalBillers.add(externalBiller);
		externalBiller.getCustomers().add(this);
	}

	public void removeExternalBiller(ExternalBiller externalBiller) {
		this.externalBillers.remove(externalBiller);
		externalBiller.getCustomers().remove(this);
	}

	public Set<ExternalPayee> getExternalPayees() {
		return this.externalPayees;
	}

	public void setExternalPayees(Set<ExternalPayee> externalPayees) {
		this.externalPayees = externalPayees;
	}
	
	public void addExternalPayee(ExternalPayee externalPayee) {
		this.externalPayees.add(externalPayee);
		externalPayee.getCustomers().add(this);
	}

	public void removeExternalPayee(ExternalPayee externalPayee) {
		this.externalPayees.remove(externalPayee);
		externalPayee.getCustomers().remove(this);
	}

}