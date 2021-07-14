package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the Account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="account_number")
	private BigInteger accountNumber;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="current_balance")
	private BigDecimal currentBalance;

	//bi-directional many-to-one association to AccountType
	@ManyToOne
	private AccountType accountType;

	//bi-directional many-to-one association to Company_Detail
	@ManyToOne
	@JoinColumn(name="CompanyDetails_sort_code")
	private Company_Detail companyDetail;

	//bi-directional many-to-one association to AccountTransaction
	@OneToMany(mappedBy="account")
	private List<AccountTransaction> accountTransactions;

	//bi-directional many-to-many association to Customer
	@ManyToMany(mappedBy="accounts")
	private Set<Customer> customers = new HashSet<Customer>();

	public Account() {
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

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCurrentBalance() {
		return this.currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public AccountType getAccountType() {
		return this.accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Company_Detail getCompanyDetail() {
		return this.companyDetail;
	}

	public void setCompanyDetail(Company_Detail companyDetail) {
		this.companyDetail = companyDetail;
	}

	public List<AccountTransaction> getAccountTransactions() {
		return this.accountTransactions;
	}

	public void setAccountTransactions(List<AccountTransaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	public AccountTransaction addAccountTransaction(AccountTransaction accountTransaction) {
		getAccountTransactions().add(accountTransaction);
		accountTransaction.setAccount(this);

		return accountTransaction;
	}

	public AccountTransaction removeAccountTransaction(AccountTransaction accountTransaction) {
		getAccountTransactions().remove(accountTransaction);
		accountTransaction.setAccount(null);

		return accountTransaction;
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
		customer.getAccounts().add(this);
	}

	public void removeCustomer(Customer customer) {
		this.customers.remove(customer);
		customer.getAccounts().remove(this);
	}

}