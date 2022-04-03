package com.zensar.apigateway;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * API gateway  works eureka server to route requests to appropriate microservice
 * any common code which needs to be executed before and/or after every request to any microservice need
 * Any request from client will be handled by api gateway
 * Also  microservices internal communication also routed through API gateway 
 * @author NK65825
 *
 */ 
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
