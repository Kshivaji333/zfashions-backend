package com.myCart.app.usercontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myCart.app.entities.User;
import com.myCart.app.userservices.UserServiceContract;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserServiceContract userService;
	@Autowired
	public UserController(UserServiceContract userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		
		try {
			User registeredUser = userService.registerUser(user);
			
			return ResponseEntity.ok(Map.of("message", "User registered successfully", "user", Map.of("username",registeredUser.getUsername(),"role",registeredUser.getRole())));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("Error",e.getMessage()));
		}
	}
}
