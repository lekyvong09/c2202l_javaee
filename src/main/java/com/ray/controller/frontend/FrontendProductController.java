package com.ray.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.entity.Product;
import com.ray.service.ProductService;


@WebServlet("/product")
public class FrontendProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService;

    public FrontendProductController() {
        super();
        this.productService = new ProductService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theCommand = request.getParameter("command");
		
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "LIST":
				getProductList(request,response);
				break;
			default:
				getProductList(request,response);
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private void getProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> productList = null;
		request.getParameter("id");
		
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			int categoryId = Integer.valueOf(request.getParameter("id"));
			productList = productService.getProductsByCategory(categoryId);
		} else {
			productList = productService.listProduct();
		}
		
		request.setAttribute("productList", productList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontend/product_list.jsp");
		requestDispatcher.forward(request, response);
	}

}
