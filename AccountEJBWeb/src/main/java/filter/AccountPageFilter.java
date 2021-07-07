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
@WebFilter("/Dashboard")
public class AccountPageFilter implements Filter {

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

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("In do filter method");
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("username") == null) {
			System.out.println("In do filter if where session is null or username is null");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
//			response.sendRedirect(request.getContextPath() + "/Login?action=login");
			request.getRequestDispatcher("/Login?action=login").forward(request, response);
		} else {
			System.out.println("In do filter else where session or username not null" + session + " "
					+ session.getAttribute("username"));
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
