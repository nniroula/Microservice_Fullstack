package com.persistentcoding.order_microservice.feignClient;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name="PRODUCT-MICROSERVICE")
public interface FeignClientProductProxyInterface {
	/* for product service connection  localhost:8081/products/{skuCode}/price */
	@GetMapping("/api/products/{skuCode}/price")
	public BigDecimal getProductUnitPrice(@PathVariable String skuCode);
	
	@GetMapping("/api/products/{skuCode}/name")
	public String getProductNameBySkuCode(@PathVariable("skuCode") String skuCode);
	
	@GetMapping("/api/products/{skuCode}/description")
	public String getProductDescriptionBySkuCode(@PathVariable("skuCode") String skuCode);
}