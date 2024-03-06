create database c2202l_javaEE_ebook;
use c2202l_javaEE_ebook;

DROP TABLE IF EXISTS c2202l_javaEE_ebook.users ;
CREATE TABLE IF NOT EXISTS c2202l_javaEE_ebook.users (
	user_id int(11) NOT NULL AUTO_INCREMENT,
	email varchar(30) NOT NULL,
	password varchar(512) NOT NULL,
	full_name varchar(30) NOT NULL,
	PRIMARY KEY (user_id)
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS c2202l_javaEE_ebook.category ;
CREATE TABLE IF NOT EXISTS c2202l_javaEE_ebook.category (
	category_id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(30) NOT NULL,
	PRIMARY KEY (category_id) 
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS c2202l_javaEE_ebook.product ;
CREATE TABLE IF NOT EXISTS c2202l_javaEE_ebook.product (
	product_id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(128) NOT NULL,
	author varchar(64) NOT NULL,
	description mediumtext NOT NULL,
	isbn varchar(15) NOT NULL,
	image longblob NOT NULL,
	price DECIMAL(13,2) NOT NULL,
	publish_date date NOT NULL,
	last_update_time datetime NOT NULL,
	category_id int(11) NOT NULL,
	PRIMARY KEY (product_id),
	CONSTRAINT product_category_fk FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS c2202l_javaEE_ebook.customer ;
CREATE TABLE IF NOT EXISTS c2202l_javaEE_ebook.customer (
	customer_id int(11) NOT NULL AUTO_INCREMENT,
	email varchar(64) NOT NULL,
	fullname varchar(30) NOT NULL,
	address varchar(128) NOT NULL,
	city varchar(32) NOT NULL,
	country varchar(64) NOT NULL,
	phone varchar(15) NOT NULL,
	zipcode varchar(24) NOT NULL,
	password varchar(512) NOT NULL,
	register_date datetime NOT NULL,
	PRIMARY KEY (customer_id)
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS c2202l_javaEE_ebook.review ;
CREATE TABLE IF NOT EXISTS c2202l_javaEE_ebook.review (
	review_id int(11) NOT NULL AUTO_INCREMENT,
	product_id int(11) NOT NULL,
	customer_id int(11) NOT NULL,
	rating int(11) NOT NULL,
	headline varchar(128) NOT NULL,
	comment varchar(500) NOT NULL,
	review_time datetime NOT NULL,
	PRIMARY KEY (review_id),
	CONSTRAINT review_product_fk FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT review_customer_fk FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS c2202l_javaEE_ebook.orders ;
CREATE TABLE IF NOT EXISTS c2202l_javaEE_ebook.orders (
	order_id int(11) NOT NULL AUTO_INCREMENT,
	customer_id int(11) NOT NULL,
	order_date datetime NOT NULL,
	shipping_address varchar(256) NOT NULL,
	recipient_name varchar(30) NOT NULL,
	recipient_phone varchar(15) NOT NULL,
	payment_method varchar(20) NOT NULL,
	total DECIMAL(13,2) NOT NULL,
	status varchar(20) NOT NULL,
	PRIMARY KEY (order_id),
CONSTRAINT orders_customer_fk FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS c2202l_javaEE_ebook.order_detail ;
CREATE TABLE IF NOT EXISTS c2202l_javaEE_ebook.order_detail (
	order_detail_id int(11) NOT NULL AUTO_INCREMENT,
	order_id int(11) DEFAULT NULL,
	product_id int(11) DEFAULT NULL,
	quantity int(11) NOT NULL,
	subtotal DECIMAL(13,2) NOT NULL,
	PRIMARY KEY (order_detail_id),
	CONSTRAINT order_detail_product_fk FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT order_detail_order_fk FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB CHARSET=utf8;