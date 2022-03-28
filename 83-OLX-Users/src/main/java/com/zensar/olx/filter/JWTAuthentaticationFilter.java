package com.zensar.olx.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.zensar.olx.db.TokenStorage;
import com.zensar.olx.util.JwtUtil;

public class JWTAuthentaticationFilter extends BasicAuthenticationFilter{
	//Authorization is predefined header
	private String authorizationHeader = "Authorization";
	private final String BEARER = "Bearer ";
	//This is custom filter

	public JWTAuthentaticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);

	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		JwtUtil jwtUtil = new JwtUtil();

		System.out.println("IN doFilterInternal");
		//1.Check if user has passed token,we do that by fetching value from Authorization header
		String authorizationHeaderValue =	request.getHeader("Authorization");

		//if token is not passed OR if it does not start with bearer
		//Dont do anything procced to next filter 
		if(authorizationHeaderValue== null || !authorizationHeaderValue.startsWith(BEARER))
		{
			chain.doFilter(request, response);//invoke next security filter
			return;

		}
		if(authorizationHeaderValue!=null && authorizationHeaderValue.startsWith(BEARER))
		{
			//Bearer is a prefix to token value this is pre defined
			String token = authorizationHeaderValue.substring(7).trim();//We want to remove "Bearer " from token value 
            System.out.println("token value= "+token);
            //check if this token exists in cache
            String tokenExists = TokenStorage.getToken(token);
            
            //if token is null means user has logged out
			if(tokenExists ==null)
				{

				chain.doFilter(request, response);//invoke next security filter
				return;
				}
				//Authorization bearer token
				System.out.println("authorizationHeaderValue------>" + authorizationHeaderValue);
				System.out.println("Token value----------->"+token);


				try {
					//Validate the token
					String encodedpayload = jwtUtil.ValidateToken(token);
					//token is valid
					String actualPayload = new String(Base64.getDecoder().decode(encodedpayload));
					//From this payload we need to fetch username
					JsonParser jsonParser = JsonParserFactory.getJsonParser();
					Map<String, Object> parseMap = jsonParser.parseMap(actualPayload);
					String username = (String) parseMap.get("username");

					//Create UsernamepasswordAuthenticationToken
					UsernamePasswordAuthenticationToken authenticationToken;
					authenticationToken = new UsernamePasswordAuthenticationToken(username,
							                                                       null,
							                                                       AuthorityUtils.createAuthorityList("ROLE_USER"));
					//Authenticate user
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
				catch(Exception e)
				{
					//If token is not valid

					e.printStackTrace();
				}

				//2.If token not present ask user to login
				//3.If token present fetch it and validates it

			}


		
		chain.doFilter(request, response);
}
}
