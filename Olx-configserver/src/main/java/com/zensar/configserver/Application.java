package com.zensar.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
/**
 * 
 * @author NK65825
 *@EnableConfigServer - this annotation makes this application as config server
 *Config server acts as client to Eureka Server.
 *
 *By default  ConfigServer provides following REST enpoints
 *http://localhost:8888/application/default
 */
@EnableConfigServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
