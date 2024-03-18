package com.ray.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.service.UserService;
import com.ray.utilities.CryptoUtil;


@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService;

    public LoginServlet() {
        super();
        userService = new UserService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userEmail");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/login.jsp");
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String errorMessage = this.userService.checkLogin(
				email, 
				CryptoUtil.hashPassword(password, getServletContext().getInitParameter("salt")));
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/login.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userEmail", email);
		
		response.sendRedirect("manage_user?command=LIST");
	}

}
