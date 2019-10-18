package com.learning.edubrains.model;

import lombok.Data;

@Data
public class User {

	private String id;

//	@NotEmpty(message = "UserName should not be Empty")
	private String userName;

//	@NotNull(message = "Role should not be null")
	private Roles role;

	private String email;

	private String pwd;

	public User(String userName, Roles role, String email, String pwd) {
		this.userName = userName;
		this.role = role;
		this.email = email;
		this.pwd = pwd;
	}

}
