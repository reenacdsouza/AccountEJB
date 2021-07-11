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

import model.Account;
import model.Customer;

/**
 * Session Bean implementation class AccountDTO
 */
@Stateless
@LocalBean
public class AccountDTO implements AccountDTORemote {
	@PersistenceContext(unitName = "AccountEJB")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public AccountDTO() {
	}

	@Override
	public List<Map<String, String>> custAllAccounts(int custId) {

		Map<String, String> errorMap = new HashMap<>(); // HashMap to hold exception and error messages

		List<Map<String, String>> accList = new ArrayList<>(); // A list of all Account related Hashmaps

		try {
			System.out.println("In try block fetching user accounts");
			Query q = em.createQuery("SELECT c from Customer as c where c.id= :id");
			q.setParameter("id", custId);
			List<?> custList = q.getResultList();
			if (custList.isEmpty()) {
				errorMap.put("failure", "Error fetching customer details");
				accList.add(errorMap);
			} else {
				Customer cust = new Customer(); // to hold the reference of customer
				cust = (Customer) custList.get(0); // customer object
				List<Account> accounts = cust.getAccounts();
				Account acc = new Account(); // to hold the reference of each account
				for (int i = 0; i < accounts.size(); i++) {
					acc = accounts.get(i); // account object
					Map<String, String> custAccMap = new HashMap<>(); // HashMap to hold Account details
					custAccMap.put("accountNumer", acc.getAccountNumber().toString()); // account number
					custAccMap.put("accountType", acc.getAccountType().getType()); // account type
					custAccMap.put("accountBalance", acc.getCurrentBalance().toString()); // account balance
					accList.add(custAccMap); // add the single account map to the list
				}
			}
		} catch (Exception E) {
			errorMap.put("error", "An error occured while fetching customer account details. Please try again. Error: "
					+ E.getMessage());
			accList.add(errorMap);
		}
		return accList;// return the list of Account HashMaps
	}
}
