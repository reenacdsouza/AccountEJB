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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	private void getPayees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In getPayees in Payees servlet");
		int custId = (int) request.getSession().getAttribute("custId");
		List<Map<String, String>> payeeList = payeeDTO.custAllPayees(custId);
		if (payeeList.contains("errorMap")) {
			Map<String, String> errorMap = payeeList.get(0);
			if (errorMap.containsKey("error")) {
				request.setAttribute("error", errorMap.get("error"));
				System.out.println(
						"In getPayees error condition in Payees servlet" + request.getAttribute("error"));
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			} else if (errorMap.containsKey("failure")) {
				request.setAttribute("failure", errorMap.get("failure"));
				System.out.println(
						"In failure in Payees servlet getPayees" + request.getAttribute("failure"));
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} else {
			System.out.println("In success in else in Payees servlet getPayees");
			request.setAttribute("payeeList", payeeList);
			request.getRequestDispatcher("/payees.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
