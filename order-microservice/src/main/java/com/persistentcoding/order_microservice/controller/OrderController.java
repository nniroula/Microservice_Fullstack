package com.persistentcoding.order_microservice.controller;

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

import com.persistentcoding.order_microservice.modal.Order;
import com.persistentcoding.order_microservice.service.OrderService;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
	/* Inject/auto-wire order service  */
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/* create an order */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody Order newOrder) {
		orderService.createOrder(newOrder);
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
	
}


