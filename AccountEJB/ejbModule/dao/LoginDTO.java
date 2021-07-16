package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Customer;

/**
 * Session Bean implementation class LoginDTO
 */
@Stateless
@LocalBean
public class LoginDTO implements LoginDTORemote {
	@PersistenceContext(unitName = "AccountEJB")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public LoginDTO() {
	}

	@Override
	public Map<String, String> verifyUserAuth(String username, String password) {

		Map<String, String> custMap = new HashMap<>(); // HashMap to hold return value
		try {
			System.out.println("In try block verifying user");
			Query q = em.createQuery("SELECT c from Customer as c where c.username= :user and c.password= :pass");
			q.setParameter("user", username);
			q.setParameter("pass", password);
			List<?> custList = q.getResultList();
			if (custList.isEmpty()) {
				custMap.put("failure", "User does not exist or password is incorrect");
			} else {
				Customer cust = new Customer(); // to hold the reference of customer
				cust = (Customer) custList.get(0); // customer object
				custMap.put("id", Integer.toString(cust.getId()));
				custMap.put("username", cust.getUsername());
				custMap.put("first_name", cust.getFirstName());
				custMap.put("last_name", cust.getLastName());
				custMap.put("passport_number", cust.getPassportNumber());
				custMap.put("phone", cust.getPhone());
				custMap.put("email", cust.getEmail());
			}
		} catch (Exception e) {
			custMap.put("error", "An error occured while verifying your login credentials. Please try again. Error: "
					+ e.getMessage());
		}
		return custMap;
	}
}
