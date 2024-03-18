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
	
	public String updateUser(User user) {
		User existUser = this.userDAO.getUserByEmail(user.getEmail());
		 
		if (existUser != null && existUser.getUserId() != user.getUserId()) {
			return "The email is already used by other account.";
		}
		
		userDAO.update(user);
		return null;
	}
	
	
	public void deleteUser(Integer userId) {
		this.userDAO.deleteById(userId);
	}
	
	public String checkLogin(String email, String password) {
		if (!this.userDAO.checkLogin(email, password)) {
			return "Wrong username and password";
		}
		return null;
	}
	
}
