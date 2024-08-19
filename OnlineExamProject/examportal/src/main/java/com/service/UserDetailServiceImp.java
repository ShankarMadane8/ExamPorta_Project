package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.entitydao.UserRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(username);
		if(user==null) {
			System.err.println("User Not Found in UserDetailServiceImpl.class .......... Not Login");
			throw new UsernameNotFoundException("No User Found !!");
		}
		
		return user;
	}

}
