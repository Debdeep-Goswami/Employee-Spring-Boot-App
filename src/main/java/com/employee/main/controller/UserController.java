package com.employee.main.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.main.entity.User;
import com.employee.main.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void initRolesAndUser() {
		userService.initRolesAndUser();
	}
	
	@PostMapping({"/registerNewUser"})
	public User registerNewUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}
	
	@GetMapping({"/forAdmin"})
	public String forAdmin() {
		return "This url is accessible to Admin only";
	}
	
	@GetMapping({"/forUser"})
	public String forUser() {
		return "This url is accessible to User only";
	}

}
