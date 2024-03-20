package com.ray.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ray.entity.Category;
import com.ray.entity.Product;
import com.ray.service.CategoryService;
import com.ray.service.ProductService;


@WebServlet("/admin/manage_product")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 100)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	private CategoryService categoryService;
       

    public ProductController() {
        super();
        productService = new ProductService();
        categoryService = new CategoryService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String theCommand = request.getParameter("command");
		
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "LIST":
				getProductList(request, response);
				break;
			case "NEW":
				showNewForm(request, response);
				break;
			case "LOAD":
				showEditForm(request, response);
				break;
			case "DELETE":
				deleteProduct(request, response);
				break;
			default:
				getProductList(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theCommand = request.getParameter("command"); 
		
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "INSERT":
				insertProduct(request, response);
				break;
			case "UPDATE":
				updateProduct(request, response);
				break;
			default:
				getProductList(request, response);
		}
	}
	
	
	private void getProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> productList = productService.listProduct();
		
		request.setAttribute("productList", productList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product_list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("theProduct", null);
		
		List<Category> categoryList = categoryService.listCategory();
		session.setAttribute("categoryList", categoryList);
		
		response.sendRedirect("product_form.jsp");
	}

	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		Integer theProductId = Integer.valueOf(request.getParameter("productId"));
		
		Product product = this.productService.getById(theProductId);
		session.setAttribute("theProduct", product);
		
		List<Category> categoryList = categoryService.listCategory();
		session.setAttribute("categoryList", categoryList);
		
		response.sendRedirect("product_form.jsp");
	}
	
	
	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		BigDecimal price = new BigDecimal(String.valueOf(request.getParameter("price")));
		
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = this.categoryService.getById(categoryId);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("The format date is MM/dd/yyyy");
		}
		
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.setCategory(category);
		newProduct.setAuthor(author);
		newProduct.setDescription(description);
		newProduct.setIsbn(isbn);
		newProduct.setPrice(price);
		newProduct.setPublishDate(publishDate);
		
		Part filePart = request.getPart("image");
		if (filePart != null & filePart.getSize() > 0) {
			byte[] imageBytes = new byte[(int)filePart.getSize()];
			
			InputStream inputStream = filePart.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			newProduct.setImage(imageBytes);
		}
		
		
		String errorMessage = productService.createProduct(newProduct);
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theProduct", newProduct);
			RequestDispatcher rd = request.getRequestDispatcher("product_form.jsp");
			rd.forward(request, response);
			return;
		}
		
		response.sendRedirect("manage_product?command=LIST");
	}
	
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer productId = Integer.valueOf(request.getParameter("productId"));
		
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		BigDecimal price = new BigDecimal(String.valueOf(request.getParameter("price")));
		
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = this.categoryService.getById(categoryId);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("The format date is MM/dd/yyyy");
		}
		
		Product updateProduct = productService.getById(productId);
		updateProduct.setName(name);
		updateProduct.setCategory(category);
		updateProduct.setAuthor(author);
		updateProduct.setDescription(description);
		updateProduct.setIsbn(isbn);
		updateProduct.setPrice(price);
		updateProduct.setPublishDate(publishDate);
		
		Part filePart = request.getPart("image");
		if (filePart != null & filePart.getSize() > 0) {
			byte[] imageBytes = new byte[(int)filePart.getSize()];
			
			InputStream inputStream = filePart.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			updateProduct.setImage(imageBytes);
		}
		
		
		String errorMessage = productService.updateProduct(updateProduct);
		
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theProduct", updateProduct);
			RequestDispatcher rd = request.getRequestDispatcher("product_form.jsp");
			rd.forward(request, response);
			return;
		}
		
		response.sendRedirect("manage_product?command=LIST");
	}


	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer productId = Integer.valueOf(request.getParameter("productId"));
		this.productService.deleteProduct(productId);
		response.sendRedirect("manage_product?command=LIST");
	}

}
