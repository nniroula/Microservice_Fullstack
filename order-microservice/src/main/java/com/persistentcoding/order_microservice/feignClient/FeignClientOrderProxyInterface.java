package com.persistentcoding.order_microservice.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.persistentcoding.order_microservice.modal.Order;

import java.math.BigDecimal;
import java.util.Optional;

/* How to add feignClient to the proejct -> find a ms project that needs to communicate with another ms project.
 * in that, create an interface - use @FeignClient(name="Name of ms service in Eureka dash board")
 *  spring.application.name = inventory-microservice
 *  */

//@FeignClient(name="INVENTORY-MICROSERVICE", name="PRODUCT-MICROSERVICE")
@FeignClient(name="INVENTORY-MICROSERVICE")
public interface FeignClientOrderProxyInterface {
	
	//Talk to inventory service response/from/inventoryService
	@GetMapping("/api/inventory/response/from/inventoryService")
	public String getResponseFromInventoryService();
	
	@RequestMapping("/api/inventory/{sku}")
	public Optional<Order> getInventoryItemBySku(@PathVariable("sku") String sku);
	
	
	@GetMapping("/api/inventory/{sku}/quantity")
	public Integer getInventoryItemCount(@PathVariable("sku") String sku);
	
	@PutMapping("/api/inventory/{sku}/update")
	public String updateInventoryItemQuantity(@PathVariable("sku") String sku, @RequestBody Order existingItem);
	
//	/* for product service connection  localhost:8081/products/{skuCode}/price */
//	@GetMapping("/{skuCode}/price")
//	public BigDecimal getProductUnitPrice(@PathVariable String skuCode);
	
	
}