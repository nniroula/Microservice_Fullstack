package com.persistentcoding.eureka_service_registry_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceRegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegistryServerApplication.class, args);
	}

}
