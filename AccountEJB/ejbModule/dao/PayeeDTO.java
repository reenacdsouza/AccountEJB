package dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Customer;
import model.ExternalPayee;

/**
 * Session Bean implementation class PayeeDTO
 */
@Stateless
@LocalBean
public class PayeeDTO implements PayeeDTORemote {
	@PersistenceContext(unitName = "AccountEJB")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public PayeeDTO() {
	}

	@Override
	public Set<Map<String, String>> custAllPayees(int custId) {

		Map<String, String> errorMap = new HashMap<>(); // HashMap to hold exception and error messages

		Set<Map<String, String>> payeeSet = new HashSet<Map<String, String>>(); // A list of all Payees Hashmaps for
																				// customer
		try {
			System.out.println("In try block fetching user payees");
			Query q = em.createQuery("SELECT c from Customer as c where c.id= :id");
			q.setParameter("id", custId);
			List<?> custList = q.getResultList();
			if (custList.isEmpty()) {
				errorMap.put("failure", "Error fetching customer payee details");
				payeeSet.add(errorMap);
			} else {
				Customer cust = new Customer(); // to hold the reference of customer
				cust = (Customer) custList.get(0); // customer object
				Set<ExternalPayee> payees = cust.getExternalPayees();
				ExternalPayee extPayee = new ExternalPayee(); // to hold the reference of each payee
				for (ExternalPayee payee : payees) {
					extPayee = payee; // payee object
					Map<String, String> custPayeeMap = new HashMap<>(); // HashMap to hold Payee details
					custPayeeMap.put("name", extPayee.getPayeeName()); // payee name
					custPayeeMap.put("branchName", extPayee.getBranchName()); // payee branch name
					custPayeeMap.put("sortCode", extPayee.getSortCode().toString()); // payee sort code
					custPayeeMap.put("accountNumber", extPayee.getAccountNumber().toString()); // payee account number
					payeeSet.add(custPayeeMap); // add the single account map to the set
				}
			}
		} catch (Exception E) {
			errorMap.put("error", "An error occured while fetching customer payee details. Please try again. Error: "
					+ E.getMessage());
			payeeSet.add(errorMap);
		}
		return payeeSet;// return the set of Payees HashMaps
	}

}
