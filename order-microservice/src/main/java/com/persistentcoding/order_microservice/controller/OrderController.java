package com.persistentcoding.order_microservice.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.persistentcoding.order_microservice.feignClient.FeignClientOrderProxyInterface;
import com.persistentcoding.order_microservice.feignClient.FeignClientProductProxyInterface;
import com.persistentcoding.order_microservice.modal.Order;
import com.persistentcoding.order_microservice.service.OrderService;


@RestController
@RequestMapping("/api/orders")

public class OrderController {
	/* Inject/auto-wire order service  */
	private final OrderService orderService;
	
	@Autowired
	private FeignClientProductProxyInterface feignProductService;
	
	@Autowired
	private FeignClientOrderProxyInterface feignClient;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	/* retrieve an order by its id */
	@GetMapping("/{id}")
	public Optional<Order> getOrderById(@PathVariable Long id) {
		return orderService.findOrderById(id);
	}
	
	/* retrieve all orders */
	@GetMapping
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	
	/* delete an order by its id */
	@DeleteMapping("/{id}")
	public String deleteOrderById(@PathVariable Long id) {
		orderService.deleteById(id);
		return "Oder with id " + id + " deleted successfully";
	}
	
	@GetMapping("/api/inventory/item/{sku}")
	public Optional<Order> getInventoryItemBySkuCode(@PathVariable String sku) {
		Optional<Order> feignOrder = feignClient.getInventoryItemBySku(sku);

		return feignOrder;
	}
	
	// Talk to inventory micro-service
	@GetMapping("/api/inventory/response/from/inventoryService")
	public String getResponseFromInventoryService(){
		String responseFromInventoryService = feignClient.getResponseFromInventoryService();
		return responseFromInventoryService;
	}
	
	// get quantity from inventory service using sku code
	@GetMapping("/api/inventory/{sku}/quantity")
	public Integer getInventoryItemCount(@PathVariable("sku") String sku) {
		int itemCount = feignClient.getInventoryItemCount(sku);
		return itemCount;
	}

	/* create an order */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String createOrder(@RequestBody Order newOrder) {
		int quanityFromUI = newOrder.getQuantity();
		String skuCodeFromUI = newOrder.getSkuCode();
		double totalCost = 0.0;
		int updatedItemCountInInventory = 0;
		Order orderToBeUpdated = new Order();
		
		// get data from Product micro service
		BigDecimal productUnitPrice = getProductUnitPrice(skuCodeFromUI);
		String productNameFromProductService = getProductNameBySkuCode(skuCodeFromUI);
		String productDescriptionFromProductService = getProductDescriptionBySkuCode(skuCodeFromUI);
		
		// check if quantity is available in inventory
		int quantityFromInventoryService = getInventoryItemCount(skuCodeFromUI);
			
		if(quantityFromInventoryService > 0 && quanityFromUI <= quantityFromInventoryService) {
			updatedItemCountInInventory = quantityFromInventoryService - quanityFromUI;
			
			// calculate total price
			productUnitPrice = getProductUnitPrice(skuCodeFromUI);
	
			totalCost = productUnitPrice.doubleValue() * quanityFromUI;
			BigDecimal totalPriceInBigDecimal = new BigDecimal(totalCost);
			
			// set up quantity, sku, name, description, total_price and unit_price for newOrder
			newOrder.setQuantity(quanityFromUI);
			newOrder.setSkuCode(skuCodeFromUI);
			newOrder.setProductName(productNameFromProductService);
			newOrder.setProductDescription(productDescriptionFromProductService);
			newOrder.setUnitPrice(productUnitPrice);
			newOrder.setTotalPrice(totalPriceInBigDecimal);
			
			// update the quantity in inventory
			orderToBeUpdated.setQuantity(updatedItemCountInInventory);
			orderToBeUpdated.setSkuCode(skuCodeFromUI);
			
			updateInventoryItemQuantity(skuCodeFromUI, orderToBeUpdated);
			
			//save order to database
			orderService.createOrder(newOrder);
			
		}else if(quanityFromUI > quantityFromInventoryService) {
			return "Not sufficient quantity";
		}
		return "Successfully placed order";
	}
		
	//	@PostMapping
	//	@ResponseStatus(HttpStatus.CREATED)
	//	public void createProductUsingProductDTO(@RequestBody OrderRequestDTO newOrderRequestDTO){
	//	orderService.createOrderUsingOrderRequestDto(newOrderRequestDTO);
	//	}
	
	
	//update inventory item count
	@PutMapping("/api/inventory/{sku}/update")
	public String updateInventoryItemQuantity(@PathVariable("sku") String sku, @RequestBody Order existingItem) {
		feignClient.updateInventoryItemQuantity(sku, existingItem);
		return "update success";
	}
	
	
	/* Product Service related method invocation */
	@GetMapping("/api/products/{skuCode}/price")
	public BigDecimal getProductUnitPrice(@PathVariable String skuCode) {
		BigDecimal productUnitPrice = feignProductService.getProductUnitPrice(skuCode);
		return productUnitPrice;
	}
	
	@GetMapping("/api/products/{skuCode}/name")
	public String getProductNameBySkuCode(@PathVariable String skuCode) {
		String productName = feignProductService.getProductNameBySkuCode(skuCode);
		return productName;
	}
	
	@GetMapping("/api/products/{skuCode}/description")
	public String getProductDescriptionBySkuCode(@PathVariable String skuCode) {
		String productDescription = feignProductService.getProductDescriptionBySkuCode(skuCode);
		return productDescription;
	}
}

