package com.controller;

import java.net.http.HttpResponse;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.config.JwtUtil;
import com.entity.JwtRequest;
import com.entity.JwtResponse;
import com.entity.User;
import com.helper.UserFoundException;
import com.helper.UserNotFoundException;
import com.service.UserDetailServiceImp;
import com.service.UserServiceImp;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	private static final String User = null;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImp userDetailServiceImp;
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	//generate Token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			
			authenticate(jwtRequest.getUserName(),jwtRequest.getPassword());
			
		} catch (UsernameNotFoundException e) {
			System.err.println("userNotFountExceptin in AuthenticationControole");
			e.printStackTrace();		
			throw new UsernameNotFoundException("User Not Found Exception");
		}
		
		
		//authenticate
		
		UserDetails userDetails = this.userDetailServiceImp.loadUserByUsername(jwtRequest.getUserName());
		String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));		
	}
	
	
	// authenticate
	public void authenticate(String UserName,String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserName, password));    
			
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED"+e.getMessage());
		}catch(BadCredentialsException e){
			throw new Exception("Inavlid Credtional"+e.getMessage());
		}
		
	}
	
	
	
	//get cuurenet user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
	    User user=(User)this.userDetailServiceImp.loadUserByUsername(principal.getName());
	    System.out.println(userServiceImp.getUser(principal.getName()));
		System.out.println("current user Details: "+user);
		return user;
	}
	

	

}
