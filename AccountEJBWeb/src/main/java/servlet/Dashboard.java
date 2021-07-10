package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
			System.out.println("In dashboard in dashboard servlet");
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
			break;
		case "showAll":
			accountDetails(request, response);
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

	private void accountDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tableStr = new String();
		List<Map<String, String>> acclist = accDTO.allAccounts();
		// now including the list inside an HTML table [as a String]
		tableStr += "<table border='1'>";
		tableStr += "<tr><td>Account Number</td><td>Current Balance</td><td>Account Type</td></tr>";
		for (int i = 0; i < acclist.size(); i++) {
			tableStr += "<tr><td>" + acclist.get(i).get("number") + "</td>" + "<td>" + acclist.get(i).get("balance")
					+ "</td>" + "<td>" + acclist.get(i).get("type") + "</td></tr>";
		}
		tableStr += "</table>";

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Dashboard</title>");
		out.println("<head>");
		// showing the Account Details as a list
		out.println("Account List Summary @ " + new java.util.Date() + "<br/>" + tableStr);
		out.println("<body>");
		out.println("</body>");
		out.println("</html>");
		out.close();

	}

}
