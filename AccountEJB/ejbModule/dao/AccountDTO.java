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
		} catch (Exception e) {
			e.printStackTrace();
			errorMap.put("error", "An error occured while fetching customer account details. Please try again.");
			accSet.add(errorMap);
		}
		return accSet;// return the list of Account HashMaps
	}

	@Override
	public boolean createAccount(int custId, String type, BigDecimal deposit) {
		try {
			System.out.println("In try block of createAccount");
			BigInteger ranNum = null;
			Account last = null;
			Query q = em.createQuery("SELECT count(a) from Account as a");
			if (q.getResultList().size() == 1) {
				long recCount = (long) q.getResultList().get(0);
				if (recCount > 0) {
					Query qe = em.createQuery("Select a from Account as a order by a.id DESC");
					qe.setMaxResults(1);
					last = (Account) qe.getSingleResult();
					ranNum = last.getAccountNumber().add(new BigInteger("1"));
					System.out.println("Random Number incremented by a value of 1 :" + ranNum);
				} else {
					ranNum = new BigInteger("900000000000");
				}
			}

			Account acc = new Account();
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

			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			Timestamp currentTimestamp = new Timestamp(now.getTime());
			acc.setCreateDate(currentTimestamp);

			acc.setCurrentBalance(deposit);

			em.persist(acc);

			Customer cust = em.find(Customer.class, custId);
			cust.addAccount(acc);

			AccountTransaction accountTran = new AccountTransaction();
			accountTran.setAccount(acc);
			accountTran.setTransactionType(em.find(TransactionType.class, 1));
			accountTran.setTransactionDescription("Account opening cash Deposit");
			accountTran.setTransactionTime(currentTimestamp);
			accountTran.setDebit(new BigDecimal("0.00"));
			accountTran.setCredit(deposit);
			accountTran.setRunningBalance(acc.getCurrentBalance());

			em.persist(accountTran);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Map<String, String> withdraw(BigInteger accountnumber, BigDecimal amount) {
		Map<String, String> transactionMap = new HashMap<>(); // HashMap to hold return value
		try {
			System.out.println("In try block of withdraw");
			Account account = null;
			Query q = em.createQuery("SELECT a from Account as a where a.accountNumber= :accountnumber");
			q.setParameter("accountnumber", accountnumber);
			account = (Account) q.getSingleResult();
			if (account != null) {
				BigDecimal currentbalance = account.getCurrentBalance();
				BigDecimal newbalance = currentbalance.subtract(amount);
				
				account.setCurrentBalance(newbalance);
				em.merge(account);
				
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				Timestamp currentTimestamp = new Timestamp(now.getTime());
				
				AccountTransaction accountTran = new AccountTransaction();
				accountTran.setAccount(account);
				accountTran.setTransactionType(em.find(TransactionType.class, 2));
				accountTran.setTransactionDescription("Cash Withdrawal");
				accountTran.setTransactionTime(currentTimestamp);
				accountTran.setDebit(new BigDecimal("0.00"));
				accountTran.setCredit(amount);
				accountTran.setRunningBalance(account.getCurrentBalance());
				
				em.persist(accountTran);
				transactionMap.put("newbalance", newbalance.toString());
			} else {
				transactionMap.put("failure", "Error performing withdrawal transaction. Please try again later");
			}
		} catch (Exception e) {
			transactionMap.put("error",
					"An error occured while performing withdrawal transaction. Please try again.");
			e.printStackTrace();
		}
		return transactionMap;
	}

	@Override
	public Map<String, String> deposit(BigInteger accountnumber, BigDecimal amount) {
		Map<String, String> transactionMap = new HashMap<>(); // HashMap to hold return value
		try {
			System.out.println("In try block of deposit");
			Account account = null;
			Query q = em.createQuery("SELECT a from Account as a where a.accountNumber= :accountnumber");
			q.setParameter("accountnumber", accountnumber);
			account = (Account) q.getSingleResult();
			if (account != null) {
				BigDecimal currentbalance = account.getCurrentBalance();
				BigDecimal newbalance = currentbalance.add(amount);
				account.setCurrentBalance(newbalance);
				em.merge(account);
				
				Calendar calendar = Calendar.getInstance();
				Date now = calendar.getTime();
				Timestamp currentTimestamp = new Timestamp(now.getTime());
				
				AccountTransaction accountTran = new AccountTransaction();
				accountTran.setAccount(account);
				accountTran.setTransactionType(em.find(TransactionType.class, 1));
				accountTran.setTransactionDescription("Cash Deposit");
				accountTran.setTransactionTime(currentTimestamp);
				accountTran.setDebit(new BigDecimal("0.00"));
				accountTran.setCredit(amount);
				accountTran.setRunningBalance(account.getCurrentBalance());
				
				em.persist(accountTran);
				
				transactionMap.put("newbalance", newbalance.toString());
			} else {
				transactionMap.put("failure", "Error performing deposit transaction. Please try again later");
			}
		} catch (Exception e) {
			transactionMap.put("error",
					"An error occured while performing deposit transaction. Please try again.");
			e.printStackTrace();
		}
		return transactionMap;
	}
}
