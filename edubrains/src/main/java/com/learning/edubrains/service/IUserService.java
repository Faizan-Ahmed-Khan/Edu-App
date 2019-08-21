package com.learning.edubrains.service;

import com.learning.edubrains.model.User;

public interface IUserService {

	void addUser(User user);

	User getUser(String userName);

	User getLoggedInUser();
}
