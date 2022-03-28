package com.zensar.olx.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailsService userDetailsService;

	//UserDetailsService is an interface given by spring security
	//this interface has only one method loadUserByUserName(String userName)
	//This method is responsible for loading the user object from database
	//If user object couldn't found in database this method should throw userName not found execption
	//It is responsibilty of developer to give implementation of interface
	
	
	// httpstatus code 401 which specifies that user is not passing correct input

	// Authentication - username,password
	// Prove whatever user is claiming
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication()
		//.withUser("zensar")
		//.password("$2a$10$eQtjWA3W1NC0m6uJ4uMzmeNOxtHhPtrrbijyWMtZwtU8QaCF0xF5e")
		//.roles("USER")
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder())
		;//roles
	}

	// Authorization
	// Access based on roles

	// http status code 403(forbidden)-specify user is authenticated
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/user/authenticate","/token/validate").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.addFilter(new com.zensar.olx.filter.JWTAuthentaticationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//httpBasic();
	}
		@Bean
		@Override
		 protected AuthenticationManager authenticationManager() throws Exception
		{
			return super.authenticationManager();
		}
	

	@Bean
	public  PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
		return PasswordEncoder;
	}

}
