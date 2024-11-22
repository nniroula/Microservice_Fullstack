package com.persistentcoding.order_microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.persistentcoding.order_microservice.modal.Order;
import com.persistentcoding.order_microservice.repo.OrderRepository;


@Service
public class OrderService {
	private final OrderRepository orderRepo;
	
	// constructor dependency injection
	public OrderService(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	/* create order */
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}
	
	/* retrieve an order by its ID */
	public Optional<Order> findOrderById(long id) {
		return orderRepo.findById(id);
	}
	
	/* retrieve all orders */
	public List<Order> getAllOrders(){
		return orderRepo.findAll();	
	}
	
	/* delete order by its ID */
	public void deleteById(long id) {
		orderRepo.deleteById(id);
	}
	
}
