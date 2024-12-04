package com.persistentcoding.inventory_microservice.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/* dto = Data Transfer Object */

public class InventoryRequestDTO {
	/* fields are same as in inventory modal class */
	
	private Long id;
	private String skuCode;
	private Integer quantity;
	
	
	/* constructors */
	public InventoryRequestDTO() {
		
	}

	/* getter and setters */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
