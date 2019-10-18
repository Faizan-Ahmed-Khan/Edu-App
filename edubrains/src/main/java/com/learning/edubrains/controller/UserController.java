package com.learning.edubrains.controller;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.edubrains.model.AuthData;
import com.learning.edubrains.model.Roles;
import com.learning.edubrains.model.User;
import com.learning.edubrains.repo.ISessionUserRepo;
import com.learning.edubrains.service.IUserService;
import com.learning.edubrains.utils.EduAppServiceException;
import com.learning.edubrains.utils.ResponseObject;
import com.learning.edubrains.utils.ValdatorUtils;

@RestController
@RequestMapping(value = "/services")
public class UserController {

	@Autowired
	private IUserService usrService;

	@Autowired
	private ValdatorUtils validator;

	@Autowired
	ISessionUserRepo repo;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/user")
	public ResponseEntity<ResponseObject> createUser(User user) {
		ResponseObject resp = new ResponseObject();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand("user").toUri();
//		User user = new User(name, role, auth.getEmail(), auth.getPassword());
		if (usrService.addUser(user)) {
			resp.setMessage("User Added Successfully");
			resp.setStatusCode(HttpStatus.CREATED);
		} else {
			resp.setMessage("Could not create the user");
			resp.setStatusCode(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(resp);
		}
		return ResponseEntity.created(location).body(resp);
	}

	@GetMapping(value = "/user")
	public ResponseEntity<ResponseObject> getUser(@RequestParam String userName, @RequestHeader String accessId) {
		logger.info("Get User:: {}", userName);
		ResponseObject resp = new ResponseObject();

		if (validator.validateEmptyOrNull(userName)) {
			User u = usrService.getUser(userName);
			if (!Optional.ofNullable(u).isPresent()) {
//				resp.setMessage("User Does not Exist");
//				resp.setStatusCode(HttpStatus.BAD_REQUEST);
				throw new EduAppServiceException("User Does not Exist");
			} else {
				resp.setMessage(u.toString());
				resp.setStatusCode(HttpStatus.OK);
			}

			return ResponseEntity.ok(resp);
		} else {
			resp.setMessage("UserName cannot be null or Empty");
			resp.setStatusCode(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(resp);
		}
	}
}
