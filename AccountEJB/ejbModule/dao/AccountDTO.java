package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;


/**
 * Session Bean implementation class AccountDTO
 */
@Stateless
@LocalBean
public class AccountDTO implements AccountDTORemote {
	@PersistenceContext(unitName="AccountEJB")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public AccountDTO() {
    }

	@Override
	public List<Map<String, String>> allAccounts() {
		List<?> queryResults = em.createQuery("SELECT b FROM Account b").getResultList();
    	List<Map<String, String>> lst = new ArrayList<>(); // A list of all Account related dictionary/Hashmap
    	Account ba = new Account(); // to hold the reference of each account
    	for (int i = 0; i < queryResults.size(); i++) {
            ba = (Account)queryResults.get(i); // account object
            Map<String, String> hmap = new HashMap<>(); // HashMap/dictionary to hold Account details
            hmap.put("number", ba.getAccountNumber().toString()); // name of the account
            hmap.put("balance", ba.getCurrentBalance().toString()); // balance of the account
            // get the account type
            hmap.put("type", ba.getAccountType().getType());
            lst.add(hmap); // adding the HashMap inside the list
        }
        return lst; // return the list of Account HashMap/Dictionary
	}

}
