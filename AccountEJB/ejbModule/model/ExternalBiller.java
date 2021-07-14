package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the ExternalBiller database table.
 * 
 */
@Entity
@NamedQuery(name = "ExternalBiller.findAll", query = "SELECT e FROM ExternalBiller e")
public class ExternalBiller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "account_number")
	private BigInteger accountNumber;

	@Column(name = "biller_name")
	private String billerName;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "sort_code")
	private BigInteger sortCode;

	// bi-directional many-to-many association to Customer
	@ManyToMany(mappedBy = "externalBillers")
	private Set<Customer> customers = new HashSet<Customer>();

	public ExternalBiller() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(BigInteger accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBillerName() {
		return this.billerName;
	}

	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public BigInteger getSortCode() {
		return this.sortCode;
	}

	public void setSortCode(BigInteger sortCode) {
		this.sortCode = sortCode;
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		this.customers.add(customer);
		customer.getExternalBillers().add(this);
	}

	public void removeCustomer(Customer customer) {
		this.customers.remove(customer);
		customer.getExternalBillers().remove(this);
	}

}