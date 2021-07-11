package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		default:
			break;
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
		List<Map<String, String>> accList = accDTO.custAllAccounts(custId);
		if (accList.contains("errorMap")) {
			Map<String, String> errorMap = accList.get(0);
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
		} else {
			System.out.println("In success in else in dashboard servlet getCustAccounts");
			request.setAttribute("accList", accList);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}

	}

}
