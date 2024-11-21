package com.persistentcoding.product_microservice.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;

/* dto = Data Transfer Object */

public class ProductDTO {
	/* fields are same as in product modal class */
	private String name;
	private String description;
	private BigDecimal price;

	
	/* constructors */
	public ProductDTO() {
		
	}
	
	public ProductDTO(String name, String description, BigDecimal price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/* getter and setters */
	
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
