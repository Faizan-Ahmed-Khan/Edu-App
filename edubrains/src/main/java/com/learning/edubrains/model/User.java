package com.learning.edubrains.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class User {

	private String id;

	@NotEmpty(message = "UserName should not be Empty")
	private String userName;

	@NotNull(message = "Role should not be null")
	private Roles role;

}
