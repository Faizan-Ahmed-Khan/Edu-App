package com.learning.edubrains.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.learning.edubrains.utils.EduAppServiceException;
import com.learning.edubrains.utils.ResponseObject;

@RestController
@RequestMapping(value = "/services")
public class UserController {

	@Autowired
	private IUserService usrService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/user")
	public ResponseEntity<ResponseObject> addUser(@Valid @RequestBody User user) {
		logger.info("Inside Add User Method, payload:: " + user.toString());
		ResponseObject resp = new ResponseObject();
		try {
			// check if user exists
			User u = usrService.getUser(user.getUserName());
			if (null == u) {
				usrService.addUser(user);
				resp.setMessage("User Added Successfully");
				resp.setStatusCode(HttpStatus.CREATED);
			} else {
				resp.setMessage("User Already Exists, uid:: " + u.getId());
				resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand("user").toUri();
			return ResponseEntity.created(location).body(resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EduAppServiceException("Exception while adding user, message:: " + e.getMessage(), e.getCause());
		}
	}

	@GetMapping(value = "/user")
	public User getUser(@RequestParam String userName) {
		logger.info("Get User:: " + userName);
		return usrService.getUser(userName);
	}
}
