package com.ray.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/admin/*")
public class AdminLoginFilter extends HttpFilter implements Filter {
       

    private static final long serialVersionUID = 1L;


	public AdminLoginFilter() {
        super();
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		HttpSession session = httpRequest.getSession(false);
		
		/// check whether user is logged in
		boolean isLoggedIn = session != null && session.getAttribute("userEmail") != null;
		
		/// http://localhost:8080/admin/login
		String loginURI = httpRequest.getContextPath() + "/admin/login";
		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		
		if (isLoggedIn || isLoginRequest || isLoginPage) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
			dispatcher.forward(request, response);
		}

	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
