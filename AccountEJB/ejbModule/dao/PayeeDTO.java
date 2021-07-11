package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Map<String, String>> custAllPayees(int custId) {
		
		Map<String, String> errorMap = new HashMap<>(); // HashMap to hold exception and error messages

		List<Map<String, String>> payeeList = new ArrayList<>(); // A list of all Payees Hashmaps for customer
		try {
			System.out.println("In try block fetching user payees");
			Query q = em.createQuery("SELECT c from Customer as c where c.id= :id");
			q.setParameter("id", custId);
			List<?> custList = q.getResultList();
			if (custList.isEmpty()) {
				errorMap.put("failure", "Error fetching customer payee details");
				payeeList.add(errorMap);
			} else {
				Customer cust = new Customer(); // to hold the reference of customer
				cust = (Customer) custList.get(0); // customer object
				List<ExternalPayee> payees = cust.getExternalPayees();
				ExternalPayee payee = new ExternalPayee(); // to hold the reference of each payee
				for (int i = 0; i < payees.size(); i++) {
					payee = payees.get(i); // payee object
					Map<String, String> custPayeeMap = new HashMap<>(); // HashMap to hold Payee details
					custPayeeMap.put("name", payee.getPayeeName()); // payee name
					custPayeeMap.put("branchName", payee.getBranchName()); // payee branch name
					custPayeeMap.put("sortCode", payee.getId().getSortCode()); // payee sort code
					custPayeeMap.put("accountNumber", payee.getId().getAccountNumber()); // payee account number
					payeeList.add(custPayeeMap); // add the single account map to the list
				}
			}
		} catch (Exception E) {
			errorMap.put("error", "An error occured while fetching customer payee details. Please try again. Error: "
					+ E.getMessage());
			payeeList.add(errorMap);
		}
		return payeeList;// return the list of Payees HashMaps
	}

}
