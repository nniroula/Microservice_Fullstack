package com.persistentcoding.inventory_microservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.persistentcoding.inventory_microservice.modal.Inventory;
import com.persistentcoding.inventory_microservice.service.InventoryService;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	// inject inventoryService -> constructor injection
	private final InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	// checks if an item is in stock
	@GetMapping("/{skuCode}")
	@ResponseStatus(HttpStatus.OK)
	public String isItemInStock(@PathVariable("skuCode") String skuCode) {
		boolean isPresentInStock = inventoryService.isProductInStock(skuCode);
		if(isPresentInStock == false) {
			return "Item not available";
		}else {
			return "Item in stock";
		}
	}
	
	// create inventory item
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createItem(@RequestBody Inventory item) {
		inventoryService.createInventoryItem(item);
	}
	
	// get all inventory items
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Inventory> getAllItems(){
		return inventoryService.getAllItemsInInventory();
	}
	
	// get an item by sku code
	@GetMapping("/item/{sku}")
	@ResponseStatus(HttpStatus.OK)
	public Inventory getItemBySku(@PathVariable String sku) {
		Optional<Inventory> itemInstock = inventoryService.getItemBySkuCode(sku);
		if(itemInstock.isPresent()) {
			return itemInstock.get();
		}else {
			return null;
		}
	}
	
	// delete item by sku code
	@DeleteMapping("/{sku}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteItemBySku(@PathVariable String sku) {
		boolean result = inventoryService.deleteItemBySkuCode(sku);
		if(result == true) {
			return "Deleted successfully";
		}else {
			return "Not in inventory";
		}
	}

}
