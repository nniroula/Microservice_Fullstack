package com.persistentcoding.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.cors.reactive.CorsWebFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class ApiGatewayConfiguration {
	// hard coded bean
	@Bean
	public RouteLocator routing(RouteLocatorBuilder routeLocatorBuilder) {
		// return builder.routes().build(); // when routes are not customized
		
		// if route is customized, do the following. use builder to customize routes
		/*
		Function<PredicateSpec, Buildable<Route>> routeFunction = 
				p -> p.path("/get") // .uri("http://httpbin.org:80") // in browser run localhost:8765/get without code below this line
					.filters(f -> f.addRequestHeader("MyHeader", "MyUri")
									.addRequestParameter("Param", "MyValue"))
					.uri("http://httpbin.org:80");
				
		return builder.routes().route(routeFunction).build();
		*/
		
		/*
		// Convention - route function is written in-line, but not as a separate function. So, above becomes as below
		// Note to run this, you have to comment spring.cloud.gateway.discovery.locator.enabled = true in application.properties file
		return builder.routes()
							.route(predicate -> predicate.path("/get") // .uri("http://httpbin.org:80") // in browser run localhost:8765/get without code below this line
								.filters(filter -> filter.addRequestHeader("MyHeader", "MyUri")
									.addRequestParameter("Param", "MyValue"))
								.uri("http://httpbin.org:80"))
							.route(predicate -> predicate.path("/api/products/**") // if request starts with /api/products
									.uri("lb://PRODUCT-MICROSERVICE") // name in Eureka server
									)  // in browser localhost:8765/PRODUCT-MICROSERVICE/api/products
							
							.build();
		*/
		
		// in browser localhost:8765/PRODUCT-MICROSERVICE/api/products
		return routeLocatorBuilder.routes()
						.route(predicate -> predicate.path("/api/products/**") // if request starts with /api/products
								//.filters(f -> f.stripPrefix(1))
								//.filters(f -> f.rewritePath("/PRODUCT-MICROSERVICE/(?<segment>.*)", "/${segment}"))
//								.uri("lb://PRODUCT-MICROSERVICE")) // name in Eureka server
								.uri("lb://PRODUCT-MICROSERVICE")) // name in Eureka server
								//.uri("lb://product-microservice/"))
//									
//						.route("ORDER-MICROSERVICE", predicate -> predicate.path("/api/orders/**") // if request starts with /api/products
//								.uri("lb://ORDER-MICROSERVICE/"))
//						
//						.route("INVENTORY-MICROSERVICE", predicate -> predicate.path("/api/inventory/**") // if request starts with /api/products
//								.uri("lb://INVENTORY-MICROSERVICE/"))
				
						.build();
	}
	
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // You can specify the allowed origins here
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(corsConfigurationSource);
    }
	
}
