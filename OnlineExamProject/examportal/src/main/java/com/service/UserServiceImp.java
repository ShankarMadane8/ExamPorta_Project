package com.service;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.entity.User;
import com.entity.UserRole;
import com.entitydao.RoleRepository;
import com.entitydao.UserRepository;
import com.helper.UserFoundException;
import com.helper.UserNotFoundException;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	 private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> set) throws Exception {
		
		User obj = userRepository.findByUserName(user.getUserName());
		
		if(obj!=null) {
			System.err.println("User is Already Exist......");
			System.err.println("can not save user.......");
			throw new UserFoundException("User with this Username is Already Exist !!!");
		}else {
			
			for(UserRole userRole:set) {
				roleRepository.save(userRole.getRole());
			}
				
			user.getUser_role().addAll(set);
			 user = userRepository.save(user);
			System.out.println("User Save Successfully......");
			
		}
		
		return user;
	}

	@Override
	public User getUser(String userName) {
		User user = userRepository.findByUserName(userName);
		return user;
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);		
	}

	@Override
	public User getUserById(int id) {
		Optional<User> findById = userRepository.findById(id);
		return findById.get();
	}

	@Override
	public User updateUser(User user) {
		User user2 = userRepository.save(user);
		return user2;
	}

}
