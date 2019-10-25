package com.learning.edubrains.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	private int port; // helps to read the current port

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testCreateUser() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/services/user"), HttpMethod.POST,
				entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		System.out.println("actual:: " + actual);
		assertTrue(actual.contains("/services/user"));
	}
}
