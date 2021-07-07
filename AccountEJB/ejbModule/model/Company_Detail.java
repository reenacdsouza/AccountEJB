package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Company_Details database table.
 * 
 */
@Entity
@Table(name="Company_Details")
@NamedQuery(name="Company_Detail.findAll", query="SELECT c FROM Company_Detail c")
public class Company_Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sort_code")
	private String sortCode;

	@Column(name="company_name")
	private String companyName;

	private String iban;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="companyDetail")
	private List<Account> accounts;

	public Company_Detail() {
	}

	public String getSortCode() {
		return this.sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setCompanyDetail(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setCompanyDetail(null);

		return account;
	}

}