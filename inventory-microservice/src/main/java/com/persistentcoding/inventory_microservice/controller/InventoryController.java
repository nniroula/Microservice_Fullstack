package com.persistentcoding.inventory_microservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.persistentcoding.inventory_microservice.dto.InventoryRequestDTO;
import com.persistentcoding.inventory_microservice.modal.Inventory;
import com.persistentcoding.inventory_microservice.service.InventoryService;

import jakarta.persistence.criteria.Order;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	// inject inventoryService -> constructor injection
	private final InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	// checks if an item is in stock
	@GetMapping("/item/{skuCode}")
	@ResponseStatus(HttpStatus.OK)
	public String isItemInStock(@PathVariable("skuCode") String skuCode) {
		boolean isPresentInStock = inventoryService.isProductInStock(skuCode);
		if(isPresentInStock == false) {
			return "Item not available";
		}else {
			return "Item is in stock";
		}
	}
	
	// create inventory item
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createItem(@RequestBody Inventory item) {
		inventoryService.createInventoryItem(item);
	}
	
	// create an item inventory using Inventory Request DTO class
	@PostMapping("/item")
	@ResponseStatus(HttpStatus.CREATED)
	public void createInventoryUsingInventoryRequestDTO(@RequestBody InventoryRequestDTO newInventoryRequestDTO){
		inventoryService.createInventoryUsingInventoryRequestDto(newInventoryRequestDTO);
	}

	// get all inventory items
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Inventory> getAllItems(){
		return inventoryService.getAllItemsInInventory();
	}
	
								///// GET an item by sku code //////////////////
	// get an item by sku code
	@GetMapping("/{sku}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Inventory> getInventoryItemBySku(@PathVariable String sku) {
		Optional<Inventory> itemInventory = inventoryService.getItemBySkuCode(sku);
		return itemInventory;
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
	
	// reply to order service, feign client in another service should have these mapping and method and also in its controller
	@GetMapping("/response/from/inventoryService")
	public String getResponseFromIventoryService(){
		String response = inventoryService.replyToOrderService();
		return response;
	}
	
	// get quantity of an item
	@GetMapping("/{sku}/quantity")
	public Integer getInventoryItemCount(@PathVariable("sku") String sku) {
		return inventoryService.getItemQuantity(sku);
	}
	
	// upate the quantity of an item
	@PutMapping("/{sku}/update")
	@ResponseStatus(HttpStatus.OK)
	public String updateInventoryItemQuantity(@PathVariable("sku") String sku, @RequestBody Inventory existingItem){
		String updateResult = inventoryService.updateItemQuantity(sku, existingItem);
		return updateResult;
	}

}
