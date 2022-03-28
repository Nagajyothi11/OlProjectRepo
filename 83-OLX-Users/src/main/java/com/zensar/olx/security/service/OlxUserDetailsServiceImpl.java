package com.zensar.olx.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zensar.olx.bean.OlxUser;
import com.zensar.olx.db.OlxUserDAO;






@Service
public class OlxUserDetailsServiceImpl implements UserDetailsService {
   @Autowired
	private OlxUserDAO repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//talk to data base
		OlxUser founduser = repository.findByUserName(username);
		if(founduser==null) {
			 throw new UsernameNotFoundException(username);
		}
		System.out.println("In loadUserByUsername");
		//UserDetails is an Interface given by spring security
		//We are free to implement interfaces but for simplicitly spring secutity has
		//implements userDetails information
		//Name of the Class is User
		//In this method we need to create an object of user and return it
      // if(username.equals("zensar")) {
    	String roles= founduser.getRole(); 
       
		User authenticatedUser=new User(founduser.getUserName(),founduser.getPassword(),AuthorityUtils.createAuthorityList(roles));
		System.out.println("In loadUserByUsername");
		return authenticatedUser;
	//}
       //throw new UsernameNotFoundException(username);
	}
	
}