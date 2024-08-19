package com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Role;
import com.entity.User;
import com.entity.UserRole;
import com.helper.UserFoundException;

import com.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/")
	public User insert(@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("password") String firstName,
			@RequestParam("password") String lastName,
			@RequestParam("password") String email,
			@RequestParam("password") String phone,
			@RequestParam("image") MultipartFile file) throws Exception {

		User user = new User();
		user.setUserName(userName);
		user.setPassword(passwordEncoder.encode(password));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);	
		user.setImage(file.getBytes());
		

		Role role = new Role();
		role.setRoll_name("NORMAL");

		UserRole userRoll = new UserRole();
		userRoll.setUser(user);
		userRoll.setRole(role);

		Set<UserRole> set = new HashSet<>();
		set.add(userRoll);

		User createUser = userService.createUser(user, set);
		
		byte[] imageBytes = file.getBytes();
        

        
        String fileName = user.getUsername() + ".jpg";
        //C:\Users\madan\OneDrive\Documents\iSmart Shankar\AngularProject\examfront\src\assets\newface\images
				
        Path imagePath = Paths.get("C:\\Users\\madan\\OneDrive\\Documents\\iSmart Shankar\\AngularProject\\examfront\\src\\assets\\newface\\images", fileName);
        System.out.println(imagePath);
         Files.write(imagePath, imageBytes);
         

		return createUser;

	}

	// ------------------------------

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById3(@PathVariable int id) throws IOException {
		User user = userService.getUserById(Integer.valueOf(id));
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	

	// ------------------------------

	@PostMapping("/upload/image")
	public ResponseEntity<User> uplaodImage(@RequestParam("image") MultipartFile file, @RequestParam("id") String id)
			throws IOException {

		System.out.println("save image................" + file);
		System.out.println("save image................" + id);
		User user2 = userService.getUserById(Integer.valueOf(id));
		user2.setImage(file.getBytes());

		User updateUser = userService.updateUser(user2);

		User user = new User();
		user.setImage(file.getBytes());
		
		byte[] imageBytes = Base64.getDecoder().decode(file.getBytes());
        

        
         String fileName = user.getUsername() + ".jpg";
         //C:\Users\madan\OneDrive\Documents\iSmart Shankar\AngularProject\examfront\src\assets\newface\images
				
         Path imagePath = Paths.get("C:\\Users\\madan\\OneDrive\\Documents\\iSmart Shankar\\AngularProject\\examfront\\src\\assets\\newface\\images", fileName);
         System.out.println(imagePath);
          Files.write(imagePath, imageBytes);
          
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
	}

	@GetMapping("/{userName}")
	public User getUser(@PathVariable String userName) {
		User user = userService.getUser(userName);
		return user;
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		System.out.println("dlete User Successfully.....");
		return "delete User successfully....";
	}

	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException e) {
		return new ResponseEntity<>(e, HttpStatus.FOUND);

	}

}
