package com.learning.edubrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class EdubrainsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdubrainsApplication.class, args);
	}

}
