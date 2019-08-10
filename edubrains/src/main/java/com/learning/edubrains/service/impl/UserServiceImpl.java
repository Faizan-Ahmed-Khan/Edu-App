package com.learning.edubrains.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.edubrains.model.User;
import com.learning.edubrains.repo.IRolesRepo;
import com.learning.edubrains.repo.IUserRepo;
import com.learning.edubrains.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private IRolesRepo rolesRepo;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public void addUser(User user) {
		// check if role is Valid
		if (rolesRepo.findByRoleName(user.getRole().getRoleName()) != null) {
			logger.info("Role:: " + rolesRepo.findByRoleName(user.getRole().getRoleName().toString()));
			userRepo.save(user);
		} else {
			throw new RuntimeException("Role is Invalid");
		}

	}

	@Override
	public User getUser(String userName) {
		return userRepo.findByUserName(userName);
	}

}
