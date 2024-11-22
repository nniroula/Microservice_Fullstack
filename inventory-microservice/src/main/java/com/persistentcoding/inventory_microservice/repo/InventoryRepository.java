package com.persistentcoding.inventory_microservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persistentcoding.inventory_microservice.modal.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode(String skuCode); // Spring Data JPA auto implements this

}
