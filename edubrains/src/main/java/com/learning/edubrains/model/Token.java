package com.learning.edubrains.model;

import lombok.Data;

@Data
public class Token {

	private String accessToken;

	public Token(String accessToken) {
		this.accessToken = accessToken;
	}
}