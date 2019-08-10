package com.learning.edubrains.utils;

public class EduAppServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EduAppServiceException(String message) {
		super(message, null, true, false);
	}
	
	public EduAppServiceException(String message, Throwable cause) {
		super(message, cause, true, false);
	}

}
