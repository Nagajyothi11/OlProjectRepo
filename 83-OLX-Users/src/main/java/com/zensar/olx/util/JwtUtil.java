package com.zensar.olx.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;



@Component
public class JwtUtil {
	
	public String generateToken(String username)
	{
		//generate token
		return JWT.create()
		.withClaim("username", username)
        .withExpiresAt(new Date(System.currentTimeMillis()+180000))
        .sign(Algorithm.HMAC512("secretkey"));
	}
     
	public String ValidateToken(String token)
	{
		//we need to validate token
		return JWT.require(Algorithm.HMAC512("secretkey"))
		.build()
		.verify(token)
		.getPayload();
		
	}
}