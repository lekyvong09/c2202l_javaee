package com.ray.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="product")
public class Product {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	@Id
	private Integer productId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="author")
	private String author;
	
	@Column(name="description")
	private String description;
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="image")
	private byte[] image;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="publish_date")
	private Date publishDate;
	
	@Column(name="last_update_time")
	@UpdateTimestamp
	private Date lastUpdateTime;
	
	@ManyToOne()
	@JoinColumn(name="category_id")
	private Category category;
}
