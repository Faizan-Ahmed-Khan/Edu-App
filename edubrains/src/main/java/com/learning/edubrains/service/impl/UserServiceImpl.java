package com.learning.edubrains.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.edubrains.model.User;
import com.learning.edubrains.repo.IUserRepo;
import com.learning.edubrains.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepo repo;

	@Override
	public void addUser(User user) {
		repo.save(user);
	}

	@Override
	public User getUser(String userName) {
		return repo.findByUserName(userName);
	}

}
