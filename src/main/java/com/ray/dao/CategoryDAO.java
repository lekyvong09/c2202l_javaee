package com.ray.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ray.entity.Category;


public class CategoryDAO extends JpaDAO<Category> {
	public CategoryDAO() {
		super(Category.class);
	}
	
	@Override
	public Category create(Category category) {
		return super.create(category);
	}
	
	@Override
	public Category update(Category category) {
		return super.update(category);
	}
	
	@Override
	public Category getById(Object categoryId) {
		return super.getById(categoryId);
	}
	
	@Override
	public void deleteById(Object categoryId) {
		super.deleteById(categoryId);
	}
	
	@Override
	public List<Category> getAll() {
		return super.getAll();
	}
	
	@Override
	public long getTotalRecord() {
		return super.getTotalRecord();
	}
	
	public Category getByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		
		List<Category> categoryList = super.getByNamedQueryWithParams("Category.HQL.getByName", params);
		
		if (categoryList != null && categoryList.size() > 0) {
			return categoryList.get(0);
		}
		
		return null;
	}
}
