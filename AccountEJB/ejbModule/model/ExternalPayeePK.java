package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ExternalPayee database table.
 * 
 */
@Embeddable
public class ExternalPayeePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="sort_code")
	private String sortCode;

	public ExternalPayeePK() {
	}
	public String getAccountNumber() {
		return this.accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getSortCode() {
		return this.sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExternalPayeePK)) {
			return false;
		}
		ExternalPayeePK castOther = (ExternalPayeePK)other;
		return 
			this.accountNumber.equals(castOther.accountNumber)
			&& this.sortCode.equals(castOther.sortCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountNumber.hashCode();
		hash = hash * prime + this.sortCode.hashCode();
		
		return hash;
	}
}