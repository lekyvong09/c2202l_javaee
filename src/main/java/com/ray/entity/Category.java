package com.ray.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="category")
@NamedQueries({
	@NamedQuery(name = "Category.HQL.getByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
})
public class Category {
	
	@Column(name="category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer categoryId;
	
	@Column(name="name")
	private String name;

	public Category() {
	}
	

	public Category(Integer categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}


	public Category(String name) {
		this.name = name;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}

}
