package com.ray.service;

import java.util.List;

import com.ray.dao.ProductDAO;
import com.ray.entity.Product;

public class ProductService {
	private ProductDAO productDAO;
	
	public ProductService() {
		productDAO = new ProductDAO();
	}
	
	public List<Product> listProduct() {
		return productDAO.getAll();
	}
	
	public String createProduct(Product product) {
		Product existProduct = this.productDAO.getByName(product.getName());
		
		if (existProduct != null) {
			return "The product name is alredy used.";
		}
		
		productDAO.create(product);
		return null;
	}
	
	public Product getById(Integer productId) {
		return this.productDAO.getById(productId);
	}
	
	
	public String updateProduct(Product product) {
		Product existProduct = this.productDAO.getByName(product.getName());
		
		if (existProduct != null && existProduct.getProductId() != product.getProductId()) {
			return "The product name already exist";
		}
		
		productDAO.update(product);
		return null;
	}
	
	public void deleteProduct(Integer productId) {
		this.productDAO.deleteById(productId);
	}
}
