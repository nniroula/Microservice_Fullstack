package com.persistentcoding.product_microservice.modal;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/* represents modal object table from which items can be added to database tables */

@Entity(name="product_service_table") // @Entity annotation makes table in the database
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="product_name")
	private String name;
	
	@Column(name="product_description")
	private String description;
	
	@Column(name="product_price")
	private BigDecimal price;
	
	/* constructors */
	
	public Product() {
	}
	
	public Product(String name, String description, BigDecimal price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
					/* getters and setters */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
}
