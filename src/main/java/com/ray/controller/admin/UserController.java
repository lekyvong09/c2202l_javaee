package com.ray.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.entity.User;
import com.ray.service.UserService;
import com.ray.utilities.CryptoUtil;


@WebServlet("/admin/manage_user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
       

    public UserController() {
        super();
        userService = new UserService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String theCommand = request.getParameter("command");
		
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "LIST":
				getUserList(request, response);
				break;
			case "NEW":
				showNewForm(request, response);
				break;
			case "LOAD":
				showEditForm(request, response);
				break;
			case "DELETE":
				deleteUser(request, response);
				break;
			default:
				getUserList(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theCommand = request.getParameter("command"); 
		
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "INSERT":
				insertUser(request, response);
				break;
			case "UPDATE":
				updateUser(request, response);
				break;
			default:
				getUserList(request, response);
		}
	}
	
	
	private void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		List<User> userList = userService.listUser();
		
		request.setAttribute("userList", userList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user_list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("theUser", null);
		
		response.sendRedirect("user_form.jsp");
	}

	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		Integer theUserId = Integer.valueOf(request.getParameter("userId"));
		
		User user = this.userService.getById(theUserId);
		
		session.setAttribute("theUser", user);
		
		response.sendRedirect("user_form.jsp");
	}
	
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", null);
		
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		User newUser = new User(
				email, 
				fullName, 
				CryptoUtil.hashPassword(password, getServletContext().getInitParameter("salt")));
		String errorMessage = userService.createUser(newUser);
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theUser", newUser);
			RequestDispatcher rd = request.getRequestDispatcher("user_form.jsp");
			rd.forward(request, response);
			return;
		}
		
		response.sendRedirect("manage_user?command=LIST");
	}
	
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		User user = new User(
				userId, 
				email, 
				fullName, 
				CryptoUtil.hashPassword(password, getServletContext().getInitParameter("salt")));
		String errorMessage = userService.updateUser(user);
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theUser", user);
			RequestDispatcher rd = request.getRequestDispatcher("user_form.jsp");
			rd.forward(request, response);
			return;
		}
		
		response.sendRedirect("manage_user?command=LIST");
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		this.userService.deleteUser(userId);
		response.sendRedirect("manage_user?command=LIST");
	}

}
