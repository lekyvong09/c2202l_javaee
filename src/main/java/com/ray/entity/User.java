package com.ray.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name = "User.HQL.getAll", query = "SELECT u FROM User u ORDER BY u.fullName"),
	@NamedQuery(name = "User.HQL.countAll", query = "SELECT count(*) FROM User"),
	@NamedQuery(name = "User.HQL.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User {
	@Column(name="user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="password")
	private String password;

	
	public User() {}
	
	
	public User(String email, String fullName, String password) {
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", fullName=" + fullName + ", password=" + password
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, password, userId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(password, other.password) && Objects.equals(userId, other.userId);
	}
	
	
	
}
