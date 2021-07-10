package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AccountPageFilter
 */
@WebFilter("/*")
public class AccountPageFilter implements Filter {
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * Default constructor.
	 */
	public AccountPageFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

//	private static final String[] loginRequiredUrls = { "/Dashboard", "/Payees", "/Profile", "/Transactions","/Error","/dashboard.jsp", "/error.jsp", "/payees.jsp", "/profile.jsp", "/transactions.jsp" };
	private static final String[] loginRequiredUrls = { "/Dashboard", "/Payees", "/Profile", "/Transactions",
			"/Error" };
	private static final String[] loginPageUrls = { "/Login", "/login.jsp" };

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		response = (HttpServletResponse) res;
		request = (HttpServletRequest) req;
		System.out.println("In do filter method" + request.getRequestURL().toString());
		HttpSession session = request.getSession(false);
		String encode = response.encodeURL(request.getContextPath());

		boolean loggedIn = (session != null && session.getAttribute("username") != null);
		boolean loginRequest = request.getRequestURI().equals(encode + "/Login");
		boolean loginOutRequest = request.getRequestURI().equals(encode + "/Logout");
		boolean loginPage = request.getRequestURI().endsWith("/");

		if (!loggedIn && (loginPage || loginRequest)) {
			System.out.println("Not logged in and on welcome page or Login servlet");
			chain.doFilter(request, response);
		} else if (!loggedIn && isLoginRequiredUrl()) {
			System.out.println("In do filter if where session is null or username is null");
			request.getRequestDispatcher("/Login?action=login").forward(request, response);
		} else if (loggedIn && loginOutRequest) {
			System.out.println("In do filter else where username not null and logging in again" + session + " "
					+ session.getAttribute("username"));
			request.getRequestDispatcher("/Logout?action=logout").forward(request, response);
		} else if (loggedIn && (isLoginPageUrl() || loginPage)) {
			System.out.println("In do filter else where username not null and logging in again" + session + " "
					+ session.getAttribute("username"));
			request.getRequestDispatcher("/Dashboard?action=dashboard").forward(request, response);
		} else {
			System.out.println("In do filter else" + session + " " + session.getAttribute("username"));
			chain.doFilter(request, response);
		}
	}

	private boolean isLoginPageUrl() {
		String requestURL = request.getRequestURL().toString();
		System.out.println("This is the requesturl in isLoginPageUrl method " + requestURL);
		for (String loginPageUrl : loginPageUrls) {
			if (requestURL.contains(loginPageUrl)) {
				return true;
			}
		}

		return false;
	}

	private boolean isLoginRequiredUrl() {
		String requestURL = request.getRequestURL().toString();

		for (String loginRequiredUrl : loginRequiredUrls) {
			if (requestURL.contains(loginRequiredUrl)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
