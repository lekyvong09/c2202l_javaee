package com.ray.service;

import java.util.List;

import com.ray.dao.UserDAO;
import com.ray.entity.User;

public class UserService {
	private UserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAO();
	}
	
	public List<User> listUser() {
		return userDAO.getAll();
	}
	
	public String createUser(User user) {
		User existUser = this.userDAO.getUserByEmail(user.getEmail());
		 
		if (existUser != null) {
			return "The email is already used by other account.";
		}
		
		userDAO.create(user);
		return null;
	}
	
	public User getById(Integer userId) {
		return this.userDAO.getById(userId);
	}
	
	public User updateUser(User user) {
		return userDAO.update(user);
	}
	
}
