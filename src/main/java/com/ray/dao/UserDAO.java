package com.ray.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ray.entity.User;

public class UserDAO extends JpaDAO<User> {

	public UserDAO() {
		super(User.class);
	}
	
	
	@Override
	public User create(User user) {
		return super.create(user);
	}
	
	
	@Override
	public User getById(Object id) {
		return super.getById(id);
	}
	
	@Override
	public User update(User user) {
		return super.update(user);
	}
	
	@Override
	public void deleteById(Object id) {
		super.deleteById(id);
	}
	
	@Override
	public List<User> getAll() {
		return super.getAll();
	}
	
	@Override
	public long getTotalRecord() {
		return super.getTotalRecord();
	}
	

	public List<User> getAllWithHQL() {
		return super.getAllWithHQL("User.HQL.getAll");
	}
	

	public long getTotalRecordWithHQL() {
		return super.getTotalRecordWithHQL("User.HQL.countAll");
	}
	
	public User getUserByEmail(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		
		List<User> userList = super.getByNamedQueryWithParams("User.HQL.findByEmail", params);
		
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
}
