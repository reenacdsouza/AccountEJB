package servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PayeeDTORemote;

/**
 * Servlet implementation class Payees
 */
@WebServlet("/Payees")
public class Payees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// injecting the session bean
	@EJB
	private PayeeDTORemote payeeDTO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Payees() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "payees":
			System.out.println("In payees in Payees servlet");
			getPayees(request, response);
			request.getRequestDispatcher("/payees.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}

	private void getPayees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In getPayees in Payees servlet");
		int custId = (int) request.getSession().getAttribute("custId");
		Set<Map<String, String>> payeeSet = payeeDTO.custAllPayees(custId);
		if (payeeSet.contains("errorMap")) {
			System.out.println("In getPayees payeeSet contains errorMap condition");
			for (Map<String, String> item : payeeSet) {
				Map<String, String> errorMap = item;
				if (errorMap.containsKey("error")) {
					request.setAttribute("error", errorMap.get("error"));
					System.out
							.println("In getPayees error condition in Payees servlet" + request.getAttribute("error"));
					getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				} else if (errorMap.containsKey("failure")) {
					request.setAttribute("failure", errorMap.get("failure"));
					System.out.println("In failure in Payees servlet getPayees" + request.getAttribute("failure"));
					getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}
		} else {
			System.out.println("In success in else in Payees servlet getPayees");
			request.setAttribute("payeeSet", payeeSet);
			request.getRequestDispatcher("/payees.jsp").forward(request, response);
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

}
