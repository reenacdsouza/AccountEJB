package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ExternalPayee database table.
 * 
 */
@Entity
@NamedQuery(name="ExternalPayee.findAll", query="SELECT e FROM ExternalPayee e")
public class ExternalPayee implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExternalPayeePK id;

	@Column(name="branch_name")
	private String branchName;

	@Column(name="payee_name")
	private String payeeName;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public ExternalPayee() {
	}

	public ExternalPayeePK getId() {
		return this.id;
	}

	public void setId(ExternalPayeePK id) {
		this.id = id;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPayeeName() {
		return this.payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}