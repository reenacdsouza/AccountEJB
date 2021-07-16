package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDTORemote;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// injecting the session bean
	@EJB
	private AccountDTORemote accDTO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
//		String encode = response.encodeURL(request.getContextPath());
		switch (action) {
		case "dashboard":
			System.out.println("In dashboard case in dashboard servlet");
			getCustAccounts(request, response);
			break;
		case "profile":
			System.out.println("In profile case in dashboard servlet do get");
			request.getRequestDispatcher("/profile.jsp").forward(request, response);
			break;
		case "loadcreate":
			System.out.println("In loadcreate case in dashboard servlet do get");
			request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			break;
		case "createaccount":
			System.out.println("In createaccount case in dashboard servlet do get");
			createCustAccount(request, response);
			break;
		case "loadtransaction":
			System.out.println("In loadtransaction case in dashboard servlet do get");
			loadTransaction(request, response);
			break;
		case "createtransaction":
			System.out.println("In createtransaction case in dashboard servlet do get");
			createTransaction(request, response);
			break;
		default:
			break;
		}
	}

	private void loadTransaction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In loadTransaction in dashboard servlet");
		String transaction = request.getParameter("transaction");
		String custAccountNumber = request.getParameter("custAccountNumber");
//		System.out.println("custAccountNumber from dashboard table row: " + custAccountNumber);
		String custAccountType = request.getParameter("custAccountType");
		String custAccountBalance = request.getParameter("custAccountBalance");
		Map<String, String> transactionMap = new HashMap<String, String>();
		if (transaction.equals("withdraw")) {
			System.out.println("In withdraw condition in loadTransaction in dashboard servlet");
			transactionMap.put("custAccountNumber", custAccountNumber);
			transactionMap.put("custAccountType", custAccountType);
			transactionMap.put("custAccountBalance", custAccountBalance);
			request.setAttribute("transactionMap", transactionMap);
			getServletContext().getRequestDispatcher("/withdraw.jsp").forward(request, response);
		} else if (transaction.equals("deposit")) {
			System.out.println("In deposit condition in loadTransaction in dashboard servlet");
			transactionMap.put("custAccountNumber", custAccountNumber);
			transactionMap.put("custAccountType", custAccountType);
			transactionMap.put("custAccountBalance", custAccountBalance);
			request.setAttribute("transactionMap", transactionMap);
			getServletContext().getRequestDispatcher("/deposit.jsp").forward(request, response);
		} else if (transaction.equals("transfer")) {
			System.out.println("In transfer condition in loadTransaction in dashboard servlet");
			transactionMap.put("custAccountNumber", custAccountNumber);
			transactionMap.put("custAccountType", custAccountType);
			transactionMap.put("custAccountBalance", custAccountBalance);
			request.setAttribute("transactionMap", transactionMap);
			getServletContext().getRequestDispatcher("/transfer.jsp").forward(request, response);
		}
	}

	private void createTransaction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In createTransaction in dashboard servlet");
		String transaction_type = request.getParameter("transaction_type");
		BigInteger account_number = new BigInteger(request.getParameter("account_number"));
		String account_type = request.getParameter("account_type");
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));

		Map<String, String> accountTransactionMap = new HashMap<String, String>();
		Map<String, String> transactionMap = new HashMap<String, String>();
		transactionMap.put("custAccountNumber", account_number.toString());
		transactionMap.put("custAccountType", account_type);
	

		if (transaction_type.equals("withdraw")) {
			System.out.println("In withdraw condition in createTransaction in dashboard servlet");
			accountTransactionMap = accDTO.withdraw(account_number, amount);
			if (accountTransactionMap.containsKey("error")) {
				System.out.println(
						"In error in dashboard servlet createTransaction withdraw" + accountTransactionMap.get("error"));
				transactionMap.put("error", accountTransactionMap.get("error"));
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			} else if (accountTransactionMap.containsKey("failure")) {
				transactionMap.put("failure", accountTransactionMap.get("failure"));
				System.out.println(
						"In failure in dashboard servlet createTransaction withdraw" + transactionMap.get("failure"));
				getServletContext().getRequestDispatcher("/withdraw.jsp").forward(request, response);
			} else {
				System.out.println("In success else in dashboard servlet createTransaction withdraw");
				String accountnumber = account_number.toString();
				transactionMap.put("success", "Withdrawal Complete");
				transactionMap.put("newbalance", accountTransactionMap.get("newbalance"));
				transactionMap.put("custAccountBalance", accountTransactionMap.get("newbalance"));
				transactionMap.put("accountnumber", accountnumber);
				transactionMap.put("accounttype", account_type);
				request.setAttribute("transactionMap", transactionMap);
				getServletContext().getRequestDispatcher("/withdraw.jsp").forward(request, response);
			}
		} else if (transaction_type.equals("deposit")) {
			System.out.println("In deposit condition in createTransaction in dashboard servlet");
			accountTransactionMap = accDTO.deposit(account_number, amount);
			if (accountTransactionMap.containsKey("error")) {
				System.out.println(
						"In error in dashboard servlet createTransaction deposit" + accountTransactionMap.get("error"));
				transactionMap.put("error", accountTransactionMap.get("error"));
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			} else if (transactionMap.containsKey("failure")) {
				transactionMap.put("failure", accountTransactionMap.get("failure"));
				System.out.println(
						"In failure in dashboard servlet createTransaction deposit" + transactionMap.get("failure"));
				getServletContext().getRequestDispatcher("/deposit.jsp").forward(request, response);
			} else {
				System.out.println("In success else in dashboard servlet createTransaction deposit");
				String accountnumber = account_number.toString();
				transactionMap.put("success", "Deposit Complete");
				transactionMap.put("newbalance", accountTransactionMap.get("newbalance"));
				transactionMap.put("custAccountBalance", accountTransactionMap.get("newbalance"));
				transactionMap.put("accountnumber", accountnumber);
				transactionMap.put("accounttype", account_type);
				request.setAttribute("transactionMap", transactionMap);
				getServletContext().getRequestDispatcher("/deposit.jsp").forward(request, response);
			}
		} else if (transaction_type.equals("transfer")) {
			System.out.println("In transfer condition in createTransaction in dashboard servlet");
		}
	}

	private void createCustAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In createCustAccount in dashboard servlet");
		int custId = (int) request.getSession().getAttribute("custId");
		String type = request.getParameter("type");
		String dep = request.getParameter("dep");
		BigDecimal deposit = new BigDecimal(dep);
		Boolean accCreated = accDTO.createAccount(custId, type, deposit);
		if (accCreated) {
			System.out.println("In success in if in dashboard servlet createCustAccount");
			request.setAttribute("success", "Account Created");
			request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
		} else {
			System.out.println("In failure in if in dashboard servlet createCustAccount");
			request.setAttribute("failure", "Error creating account. Please try again later");
			request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getCustAccounts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In getCustAccounts in dashboard servlet");
		int custId = (int) request.getSession().getAttribute("custId");
		Set<Map<String, String>> accSet = accDTO.custAllAccounts(custId);
		if (accSet.contains("errorMap")) {
			System.out.println("In getCustAccounts accountset contains errorMap condition");
			for (Map<String, String> item : accSet) {
				Map<String, String> errorMap = item;
				if (errorMap.containsKey("error")) {
					request.setAttribute("error", errorMap.get("error"));
					System.out.println(
							"In getCustAccounts error condition in dashboard servlet" + request.getAttribute("error"));
					getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				} else if (errorMap.containsKey("failure")) {
					request.setAttribute("failure", errorMap.get("failure"));
					System.out.println(
							"In failure in dashboard servlet getCustAccounts" + request.getAttribute("failure"));
					getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}

		} else {
			System.out.println("In success in else in dashboard servlet getCustAccounts");
			request.setAttribute("accSet", accSet);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}

	}

}
