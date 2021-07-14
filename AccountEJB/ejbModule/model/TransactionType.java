package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TransactionType database table.
 * 
 */
@Entity
@NamedQuery(name="TransactionType.findAll", query="SELECT t FROM TransactionType t")
public class TransactionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="source_description")
	private String sourceDescription;

	@Column(name="traget_description")
	private String tragetDescription;

	private String type;

	//bi-directional many-to-one association to AccountTransaction
	@OneToMany(mappedBy="transactionType")
	private List<AccountTransaction> accountTransactions;

	public TransactionType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceDescription() {
		return this.sourceDescription;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public String getTragetDescription() {
		return this.tragetDescription;
	}

	public void setTragetDescription(String tragetDescription) {
		this.tragetDescription = tragetDescription;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AccountTransaction> getAccountTransactions() {
		return this.accountTransactions;
	}

	public void setAccountTransactions(List<AccountTransaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	public AccountTransaction addAccountTransaction(AccountTransaction accountTransaction) {
		getAccountTransactions().add(accountTransaction);
		accountTransaction.setTransactionType(this);

		return accountTransaction;
	}

	public AccountTransaction removeAccountTransaction(AccountTransaction accountTransaction) {
		getAccountTransactions().remove(accountTransaction);
		accountTransaction.setTransactionType(null);

		return accountTransaction;
	}

}