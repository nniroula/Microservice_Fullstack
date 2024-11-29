package com.persistentcoding.order_microservice.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/* dto = Data Transfer Object */

public class OrderResponseDTO {
	/* fields are same as in product modal class */
	private long id;
	private String name;
	private String description;
	private BigDecimal price;

	
	/* constructors */
//	public ProductDTO() {
//		
//	}
//	public ProductDTO(String name, String description, BigDecimal price) {
//		super();
//		this.name = name;
//		this.description = description;
//		this.price = price;
//	}

	/* getter and setters */
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
