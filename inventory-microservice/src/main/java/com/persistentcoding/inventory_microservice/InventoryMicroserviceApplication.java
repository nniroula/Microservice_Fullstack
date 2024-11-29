package com.persistentcoding.inventory_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//import com.persistentcoding.order_microservice.EnableEurekaServer;


@SpringBootApplication
//@EnableFeignClients
@EnableDiscoveryClient
//@EnableEurekaServer
public class InventoryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryMicroserviceApplication.class, args);
	}

}
