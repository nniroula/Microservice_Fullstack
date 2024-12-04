package com.persistentcoding.inventory_microservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfigurarion {
	// create a bean so that you can auto-wire model-mapper where-ever you need it to
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}