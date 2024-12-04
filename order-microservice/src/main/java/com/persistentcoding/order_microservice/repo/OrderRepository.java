package com.persistentcoding.order_microservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persistentcoding.order_microservice.modal.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
