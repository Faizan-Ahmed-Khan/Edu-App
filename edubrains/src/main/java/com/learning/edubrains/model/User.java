package com.learning.edubrains.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class User {

	private String userId;

	private String userName;

	private Roles role;

}
