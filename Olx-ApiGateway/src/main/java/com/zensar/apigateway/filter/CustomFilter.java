package com.zensar.apigateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
/**
 * 
 * @author NK65825
 *GlaoblalFilter is an interface given by aPI gateway
 */
//@Configuration
public class CustomFilter implements GlobalFilter {
	/**
	 * Following method is opportunity for us to do some pre-processing
	 * This method automatically executes
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("in filter------> doing processing");
		/**
		 * Pre-processing logic
		 */
		ServerHttpRequest request = exchange.getRequest();
		HttpHeaders headers = request.getHeaders();
		List<String> list  = headers.get("Authorization");

		if(list!=null)
		{

			String authorizationHeaderValue = list.get(0);
		
		if(authorizationHeaderValue==null)
		{
			/**
			 * pre-processing logic fails then dont allow request to proceed
			 */
			ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return (response).setComplete();
		}
		}
		else 
		{
			/**
			 * pre-processing logic fails then dont allow request to proceed
			 */
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return (response).setComplete();
		}
		/**
		 * After successful pre-processing this method MUST call filter() method on chain object
		 */
		return chain.filter(exchange);
	}

}
