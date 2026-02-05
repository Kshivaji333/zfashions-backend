package com.myCart.app.userserviceimplimentations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myCart.app.entities.User;
import com.myCart.app.userrepositories.UserRepository;
import com.myCart.app.userservices.UserServiceContract;

@Service
public class UserService implements UserServiceContract{
	
	private final  UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}


	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("Username is already taken");
		}
		
		if(userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("Email is already rigistered");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
		
		
	}
	
	
	
	
}
