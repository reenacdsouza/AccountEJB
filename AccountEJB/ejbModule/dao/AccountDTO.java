package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Calendar;
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

import model.Account;
import model.AccountTransaction;
import model.AccountType;
import model.Company_Detail;
import model.Customer;
import model.TransactionType;

/**
 * Session Bean implementation class AccountDTO
 */
@Stateless
@LocalBean
public class AccountDTO implements AccountDTORemote {
	@PersistenceContext(unitName = "AccountEJB")
	EntityManager em;
	private int random = 1000;
	private BigInteger ranNum = BigInteger.valueOf((long) (0.00));

	/**
	 * Default constructor.
	 */
	public AccountDTO() {
	}

	@Override
	public Set<Map<String, String>> custAllAccounts(int custId) {

		Map<String, String> errorMap = new HashMap<>(); // HashMap to hold exception and error messages

		Set<Map<String, String>> accSet = new HashSet<Map<String, String>>(); // A Hashset of all Account related
																				// Hashmaps

		try {
			System.out.println("In try block fetching user accounts");
			Query q = em.createQuery("SELECT c from Customer as c where c.id= :id");
			q.setParameter("id", custId);
			List<?> custList = q.getResultList();
			if (custList.isEmpty()) {
				errorMap.put("failure", "Error fetching customer details");
				accSet.add(errorMap);
			} else {
				Customer cust = new Customer(); // to hold the reference of customer
				cust = (Customer) custList.get(0); // customer object
				Set<Account> accounts = cust.getAccounts();
				Account acc = new Account(); // to hold the reference of each account
				for (Account account : accounts) {
					acc = account; // account object
					Map<String, String> custAccMap = new HashMap<>(); // HashMap to hold Account details
					custAccMap.put("accountNumber", acc.getAccountNumber().toString()); // account number
					custAccMap.put("accountType", acc.getAccountType().getType()); // account type
					custAccMap.put("accountBalance", acc.getCurrentBalance().toString()); // account balance
					accSet.add(custAccMap); // add the single account map to the list
				}
			}
		} catch (Exception E) {
			errorMap.put("error", "An error occured while fetching customer account details. Please try again. Error: "
					+ E.getMessage());
			accSet.add(errorMap);
		}
		return accSet;// return the list of Account HashMaps
	}

	@Override
	public boolean createAccount(int custId, String type, BigDecimal deposit) {
		System.out.println("Random account number at the beginning of createAccount: " + ranNum);
		try {

			Account acc = new Account();

			random++;
//			System.out.println("Random constant number value: " + random);
//			System.out.println("Random account number before math random in createaccount method: " + ranNum);
			ranNum = BigInteger.valueOf((long) (Math.random() * Math.pow(10, 12) + random));
//			System.out.println("Random account number: " + ranNum);
			acc.setAccountNumber(ranNum);

			BigInteger sortcode = BigInteger.valueOf(0);
			sortcode = sortcode.add(BigInteger.valueOf(Long.parseLong("9061348285")));
			acc.setCompanyDetail(em.find(Company_Detail.class, sortcode));

			int id = 0;
			switch (type) {
			case "British Pound (GBP)":
				id = 1;
				break;
			case "United States Dollar (USD)":
				id = 2;
				break;
			case "Euro (EUR)":
				id = 3;
				break;
			default:
				break;
			}
			acc.setAccountType(em.find(AccountType.class, id));
//			System.out.println("Account type: " + acc.getAccountType().getType());

			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			Timestamp currentTimestamp = new Timestamp(now.getTime());
			acc.setCreateDate(currentTimestamp);

			acc.setCurrentBalance(deposit);
			em.persist(acc);
//			System.out.println("Account object that was persisted: " + acc);

			Customer cust = em.find(Customer.class, custId);
//			System.out.println("Customer object: " + cust);
			cust.addAccount(acc);

			AccountTransaction accountTran = new AccountTransaction();
			accountTran.setAccount(acc);
			accountTran.setTransactionType(em.find(TransactionType.class, 11));
			accountTran.setTransactionDescription("Account opening Deposit");
			accountTran.setTransactionTime(currentTimestamp);
			accountTran.setDebit(new BigDecimal("0.00"));
			accountTran.setCredit(deposit);
			accountTran.setRunningBalance(acc.getCurrentBalance());
			em.persist(accountTran);

//			System.out.println("Random number at the end of createaccount: " + ranNum);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
