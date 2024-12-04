package com.persistentcoding.inventory_microservice.dto;

/* dto = Data Transfer Object. DTO moves through out your program for data transfer */

public class InventoryResponseDTO {

/* fields are same as in inventory modal class */
	
	private Long id;
	private String skuCode;
	private Integer quantity;

	/* constructors */
	public InventoryResponseDTO() {
		
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
