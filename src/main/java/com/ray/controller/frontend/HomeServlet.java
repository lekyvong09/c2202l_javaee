package com.ray.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.entity.Category;
import com.ray.entity.Product;
import com.ray.service.CategoryService;
import com.ray.service.ProductService;


@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       

    public HomeServlet() {
        super();
        productService = new ProductService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Category> categoryList = categoryService.listCategory();
//		request.setAttribute("categoryList", categoryList);
		
		List<Product> productList = productService.getNewestProduct();
		request.setAttribute("newProductList", productList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/index.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("keyword");
		List<Product> productList = productService.searchByName(searchString);
		
		request.setAttribute("searchResult", productList);
		request.setAttribute("searchString", searchString);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/search.jsp");
		dispatcher.forward(request, response);
	}

}
