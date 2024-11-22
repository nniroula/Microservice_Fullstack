package com.persistentcoding.inventory_microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.persistentcoding.inventory_microservice.modal.Inventory;
import com.persistentcoding.inventory_microservice.repo.InventoryRepository;

/* it holds methods that invoke JpaRepositroy CRUD methods */

@Service
public class InventoryService {
	private final InventoryRepository inventoryRepo;
	
	// constructor injection
	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepo = inventoryRepository;
	}
	
	// methods to invoke JpaRepositroy CRUD methods (Spring Data JPA provides JpaRespository interface)
	public boolean isProductInStock(String skuCode) {
		Optional<Inventory> optionalContainer = inventoryRepo.findBySkuCode(skuCode);
//		if(optionalContainer.isPresent()) { }
		
		return optionalContainer.isPresent(); // returns true or false
	}
	
	/* create an inventory */
	public Inventory createInventoryItem(Inventory newInventory) {
		return inventoryRepo.save(newInventory);
	}
	
	/* retrieve inventory by sku code*/
	public Optional<Inventory> getItemBySkuCode(String skuCode) {
		return inventoryRepo.findBySkuCode(skuCode);
	}
	
	/* retrieve all inventory items */
	public List<Inventory> getAllItemsInInventory(){
		return inventoryRepo.findAll();
	}
	
	/* delete inventory by Sku */
	public boolean deleteItemBySkuCode(String skuCode) {
		boolean deleteSuccess = false;
		
		// retrieve an item and then delete it
		Optional<Inventory> optional = getItemBySkuCode(skuCode);
		if(optional.isPresent()) {
			inventoryRepo.delete(optional.get());
			deleteSuccess = true;
		}
		return deleteSuccess;
	}
}
