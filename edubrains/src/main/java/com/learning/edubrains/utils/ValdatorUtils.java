package com.learning.edubrains.utils;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ValdatorUtils {

	public boolean validateEmptyOrNull(String obj) {
		return (Optional.ofNullable(obj).isPresent() || obj.trim().isEmpty() ? false : true);
	}

}
