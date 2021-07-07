package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ExternalBiller database table.
 * 
 */
@Entity
@NamedQuery(name="ExternalBiller.findAll", query="SELECT e FROM ExternalBiller e")
public class ExternalBiller implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExternalBillerPK id;

	@Column(name="biller_name")
	private String billerName;

	@Column(name="branch_name")
	private String branchName;

	public ExternalBiller() {
	}

	public ExternalBillerPK getId() {
		return this.id;
	}

	public void setId(ExternalBillerPK id) {
		this.id = id;
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

}