package com.persistentcoding.order_microservice.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.persistentcoding.order_microservice.dto.OrderRequestDTO;
import com.persistentcoding.order_microservice.feignClient.FeignClientOrderProxyInterface;
import com.persistentcoding.order_microservice.modal.Order;
import com.persistentcoding.order_microservice.repo.OrderRepository;


@Service
public class OrderService {
	private final OrderRepository orderRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// constructor dependency injection
	public OrderService(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	/* create order */
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}
	
//	public OrderRequestDTO createOrderUsingOrderRequestDto(OrderRequestDTO orderRequestDto) {
//		
//		// convert ProductRequestDTO into Product JPA Entity and save it to database
//		Order requestDtoMappedOrder = modelMapper.map(orderRequestDto, Order.class);
//		Order createdOrder = orderRepo.save(requestDtoMappedOrder);
//		 
//		 //Convert Order JPA Entity into OrderRequestDTO type and return it
//		OrderRequestDTO createdProductRequestDTO = modelMapper.map(createdOrder, OrderRequestDTO.class);
//		return createdProductRequestDTO;
//	}
	
	
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



