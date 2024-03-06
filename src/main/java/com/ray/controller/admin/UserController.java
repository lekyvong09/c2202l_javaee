package com.ray.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.entity.User;
import com.ray.service.UserService;


@WebServlet("/admin/manage_user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
       

    public UserController() {
        super();
        userService = new UserService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		List<User> userList = userService.listUser();
		
		request.setAttribute("userList", userList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user_list.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", null);
		
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		User newUser = new User(email, fullName, password);
		String errorMessage = userService.createUser(newUser);
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("user_form.jsp");
			rd.forward(request, response);
			return;
		}
		
		response.sendRedirect("manage_user");
	}

}