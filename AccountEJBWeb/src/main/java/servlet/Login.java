package servlet;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDTORemote;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// injecting the session bean
	@EJB
	private LoginDTORemote loginDTO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
//		String encode = response.encodeURL(request.getContextPath());
		switch (action) {
		case "login":
			System.out.println("In login in login servlet do get"+request.getAttribute("failure"));
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			break;
		case "loginCheck":
			System.out.println("In loginCheck in login servlet do get");
			authenticateUser(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void authenticateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String encode = response.encodeURL(request.getContextPath());
		Map<String, String> custMap = loginDTO.verifyUserAuth(username, password);
		if (custMap.containsKey("error")) {
			System.out.println("In error in login servlet authenticate"+request.getAttribute("error"));
			request.setAttribute("error", custMap.get("error"));
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		} else if (custMap.containsKey("failure")) {
			request.setAttribute("failure", custMap.get("failure"));
			System.out.println("In failure in login servlet authenticate"+request.getAttribute("failure"));
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			System.out.println("In else in login servlet authenticate");
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(300);
			session.setAttribute("username", username);
			session.setAttribute("id", custMap.get("id"));
			session.setAttribute("username", custMap.get("username"));
			session.setAttribute("first_name", custMap.get("first_name"));
			session.setAttribute("last_name", custMap.get("last_name"));
			session.setAttribute("passport_number", custMap.get("passport_number"));
			session.setAttribute("phone", custMap.get("phone"));
			session.setAttribute("email", custMap.get("email"));
			response.sendRedirect(encode + "/Dashboard?action=dashboard");
		}
}

}
