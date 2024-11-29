package com.persistentcoding.product_microservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persistentcoding.product_microservice.modal.Product;

/*JpaRepository takes Data Type(Product class) and Primary Key type(Long) */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findBySkuCode(String skuCode);
}
