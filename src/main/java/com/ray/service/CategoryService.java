package com.ray.service;

import java.util.List;

import com.ray.dao.CategoryDAO;
import com.ray.entity.Category;

public class CategoryService {
	private CategoryDAO categoryDAO;
	
	public CategoryService() {
		categoryDAO = new CategoryDAO();
	}
	
	public List<Category> listCategory() {
		return categoryDAO.getAll();
	}
	
	public String createCategory(Category category) {
		Category existCategory = this.categoryDAO.getByName(category.getName());
		
		if (existCategory != null) {
			return "The category name is alredy used.";
		}
		
		categoryDAO.create(category);
		return null;
	}
	
	public Category getById(Integer categoryId) {
		return this.categoryDAO.getById(categoryId);
	}
	
	
	public String updateCategory(Category category) {
		Category existCategory = this.categoryDAO.getByName(category.getName());
		
		if (existCategory != null && existCategory.getCategoryId() != category.getCategoryId()) {
			return "The category name already exist";
		}
		
		categoryDAO.update(category);
		return null;
	}
	
	public void deleteCategory(Integer categoryId) {
		this.categoryDAO.deleteById(categoryId);
	}
}
