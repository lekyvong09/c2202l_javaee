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

import com.ray.entity.Category;
import com.ray.service.CategoryService;


@WebServlet("/admin/manage_category")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;
       

    public CategoryController() {
        super();
        categoryService = new CategoryService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String theCommand = request.getParameter("command");
		
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "LIST":
				getCategoryList(request, response);
				break;
			case "NEW":
				showNewForm(request, response);
				break;
			case "LOAD":
				showEditForm(request, response);
				break;
			case "DELETE":
				deleteCategory(request, response);
				break;
			default:
				getCategoryList(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theCommand = request.getParameter("command"); 
		
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "INSERT":
				insertCategory(request, response);
				break;
			case "UPDATE":
				updateCategory(request, response);
				break;
			default:
				getCategoryList(request, response);
		}
	}
	
	
	private void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService categoryService = new CategoryService();
		List<Category> categoryList = categoryService.listCategory();
		
		request.setAttribute("categoryList", categoryList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category_list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("theCategory", null);
		
		response.sendRedirect("category_form.jsp");
	}

	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		Integer theCategoryId = Integer.valueOf(request.getParameter("categoryId"));
		
		Category category = this.categoryService.getById(theCategoryId);
		
		session.setAttribute("theCategory", category);
		
		response.sendRedirect("category_form.jsp");
	}
	
	
	private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", null);
		
		String name = request.getParameter("name");
		
		Category newCategory = new Category(name);
		String errorMessage = categoryService.createCategory(newCategory);
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theCategory", newCategory);
			RequestDispatcher rd = request.getRequestDispatcher("category_form.jsp");
			rd.forward(request, response);
			return;
		}
		
		response.sendRedirect("manage_category?command=LIST");
	}
	
	
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
		String name = request.getParameter("name");

		
		Category category = new Category(categoryId, name);
		String errorMessage = categoryService.updateCategory(category);
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theCategory", category);
			RequestDispatcher rd = request.getRequestDispatcher("category_form.jsp");
			rd.forward(request, response);
			return;
		}
		
		response.sendRedirect("manage_category?command=LIST");
	}


	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
		this.categoryService.deleteCategory(categoryId);
		response.sendRedirect("manage_category?command=LIST");
	}

}
