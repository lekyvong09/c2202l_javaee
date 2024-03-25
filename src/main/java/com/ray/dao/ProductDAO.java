package com.ray.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ray.entity.Product;


public class ProductDAO extends JpaDAO<Product> {
	public ProductDAO() {
		super(Product.class);
	}
	
	@Override
	public Product create(Product product) {
		return super.create(product);
	}
	
	@Override
	public Product update(Product product) {
		return super.update(product);
	}
	
	@Override
	public Product getById(Object productId) {
		return super.getById(productId);
	}
	
	@Override
	public void deleteById(Object productId) {
		super.deleteById(productId);
	}
	
	@Override
	public List<Product> getAll() {
		return super.getAll();
	}
	
	@Override
	public long getTotalRecord() {
		return super.getTotalRecord();
	}
	
	public Product getByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		
		List<Product> productList = super.getByNamedQueryWithParams("Product.HQL.getByName", params);
		
		if (productList != null && productList.size() > 0) {
			return productList.get(0);
		}
		
		return null;
	}
	
	public List<Product> getProductByCategory(int categoryId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("categoryId", categoryId);
		
		List<Product> productList = super.getByNamedQueryWithParams("Product.HQL.getByCategory", params);
		return productList;
	}
}
