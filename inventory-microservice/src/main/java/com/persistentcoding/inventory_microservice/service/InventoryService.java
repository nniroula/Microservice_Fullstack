package com.persistentcoding.inventory_microservice.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.persistentcoding.inventory_microservice.dto.InventoryRequestDTO;
import com.persistentcoding.inventory_microservice.modal.Inventory;
import com.persistentcoding.inventory_microservice.repo.InventoryRepository;

/* it holds methods that invoke JpaRepositroy CRUD methods */

@Service
public class InventoryService {
	private final InventoryRepository inventoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment environment;
	
	// constructor injection
	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepo = inventoryRepository;
	}
	
	// methods to invoke JpaRepositroy CRUD methods (Spring Data JPA provides JpaRespository interface)
	public boolean isProductInStock(String skuCode) {
		Optional<Inventory> optionalContainer = Optional.empty();
		return optionalContainer.isPresent(); // returns true or false
	}
	
	/* create an inventory */
	public Inventory createInventoryItem(Inventory newInventory) {
		return inventoryRepo.save(newInventory);
	}
	
	//////
	//b. create a inventory using Inventory Request DTO class
	public InventoryRequestDTO createInventoryUsingInventoryRequestDto(InventoryRequestDTO inventoryRequestDto) {
		
		// convert ProductRequestDTO into Product JPA Entity and save it to database
		Inventory requestDtoMappedInventory = modelMapper.map(inventoryRequestDto, Inventory.class);
		Inventory createdInventory = inventoryRepo.save(requestDtoMappedInventory);
		 
		 //Convert Product JPA Entity into ProductRequestDTO type and return it
		InventoryRequestDTO createdInventoryRequestDTO = modelMapper.map(createdInventory, InventoryRequestDTO.class);
		return createdInventoryRequestDTO;
	}
	//////
	
	//////////////// USE THis method type ////////////////////
	
	/* retrieve inventory by sku code*/

	public Optional<Inventory> getItemBySkuCode(String skuCode) {
		//boolean result = isProductInStock(skuCode);
		Optional<Inventory> itemInventory = null;
		itemInventory = inventoryRepo.findBySkuCode(skuCode);
		if(itemInventory.isPresent()) {
			return itemInventory;
		}
		//return inventoryRepo.findBySkuCode(skuCode);
		return itemInventory;
	}
	
	
	
	/* retrieve all inventory items */
	public List<Inventory> getAllItemsInInventory(){
		return inventoryRepo.findAll();
	}
	
	/* delete inventory by Sku */
	public boolean deleteItemBySkuCode(String skuCode) {
		boolean deleteSuccess = false;
		
		// retrieve an item and then delete it
		Optional<Inventory> optional = Optional.empty();
		if(optional.isPresent()) {
			inventoryRepo.delete(optional.get());
			deleteSuccess = true;
		}
		return deleteSuccess;
	}
	
	
	
	// align them with order service
	
	// Reply to order service
	public String replyToOrderService(){
		String port = environment.getProperty("local.server.port");
		return "From Inventory Service in port number - " + port;
	}
	
	// get quantity
	public int getItemQuantity(String skuCode) {
		int quantity = 0;
		Optional<Inventory> item = getItemBySkuCode(skuCode);
		if(item.isEmpty()) {
			quantity = 0;
		}else if(item.isPresent()){
			quantity = item.get().getQuantity();
		}
		
		return quantity;
	}
	
	// upate item quantity
	// @PutMapping("/{sku}/upate")   @RequestBody Inventory existingItem
	public String updateItemQuantity(String skuCode, Inventory existingItem) {
		Optional<Inventory> itemFromInventoryDB = inventoryRepo.findBySkuCode(skuCode);

		Inventory itemFromDB = null;
		//int quantity = newQuantity;
		
		if(itemFromInventoryDB.isPresent()) {
			itemFromDB = itemFromInventoryDB.get();
			itemFromDB.setQuantity(existingItem.getQuantity());
			inventoryRepo.save(itemFromDB);
			
			return String.format("Quantity for item with sku code \"%s\" updated successfully", skuCode);
		}else {
			return String.format("Inventory for item with sku code \"%s\" not available", skuCode);
		}
		
	}
	
}
