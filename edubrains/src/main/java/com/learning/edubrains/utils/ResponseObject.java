package com.learning.edubrains.utils;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseObject {

	private String message;

	private HttpStatus statusCode;
}
