package servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;

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
		default:
			break;
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
		String fname = (String) request.getSession().getAttribute("fname");
		String lname = (String) request.getSession().getAttribute("lname");
		request.setAttribute("fname", fname);
		request.setAttribute("lname", lname);
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
