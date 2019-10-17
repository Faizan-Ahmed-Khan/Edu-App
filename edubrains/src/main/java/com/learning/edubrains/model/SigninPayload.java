package com.learning.edubrains.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SigninPayload {
	private String token;
	private User user;

}
