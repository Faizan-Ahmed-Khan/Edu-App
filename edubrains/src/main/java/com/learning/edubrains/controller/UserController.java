package com.learning.edubrains.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.edubrains.model.User;
import com.learning.edubrains.service.IUserService;

@RestController
@RequestMapping(value = "/services")
public class UserController {

	@Autowired
	IUserService usrService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/user")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		usrService.addUser(user);
		logger.info("Add User:: " + user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand("user").toUri();
		return ResponseEntity.created(location).body("User Added Successfully");
	}

	@GetMapping(value = "/user")
	public User getUser(@RequestParam String userName) {
		logger.info("Get User:: " + userName);
		return usrService.getUser(userName);
	}
}
