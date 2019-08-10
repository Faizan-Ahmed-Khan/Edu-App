package com.learning.edubrains.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Roles {

	private String _id;

	@NotEmpty(message = "roleName should not be Empty")
	private String roleName;
}
