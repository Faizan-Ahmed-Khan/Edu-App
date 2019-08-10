package com.learning.edubrains.utils;

import org.springframework.stereotype.Component;

@Component
public class ValdatorUtils {

	public boolean validateEmptyOrNull(String obj) {
		if (null == obj || obj.trim().isEmpty()) {
			return false;
		}
		return true;
	}

}
