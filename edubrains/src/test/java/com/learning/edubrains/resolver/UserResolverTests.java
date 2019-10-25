package com.learning.edubrains.resolver;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.learning.edubrains.repo.IUserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResolverTests {

	@LocalServerPort
	private int port;

	@Autowired
	GraphQLTestUtils graphQLTestUtils;

	@Autowired
	private String signInPayload;

	@Autowired
	private String logoutPayload;

	@Autowired
	private IUserRepo repo;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testSignIn() throws Exception {
		System.out.println("repo.findAll():: " + repo.findAll().isEmpty());

		assertTrue("There should be nothing in the database yet", !repo.findAll().isEmpty());

		// create the JSON version of query, that will be added to the body of request
		String payload = graphQLTestUtils.createJsonQuery(signInPayload);
		System.out.println("payload:: " + payload);
		// add the proper Content-Type header
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(GraphQLTestUtils.ENDPOINT_LOCATION),
				HttpMethod.POST, entity, String.class);

		System.out.println("response:: " + response);
		JsonNode parsedResponse = graphQLTestUtils.parse(response.getBody());
		System.out.println("parsedResponse:: " + parsedResponse);
	}

	@Test
	public void testLogout() throws Exception {

		// create the JSON version of query, that will be added to the body of request
		String payload = graphQLTestUtils.createJsonQuery(logoutPayload);
		System.out.println("payload:: " + payload);
		// add the proper Content-Type header
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(GraphQLTestUtils.ENDPOINT_LOCATION),
				HttpMethod.POST, entity, String.class);

		System.out.println("response:: " + response);
		JsonNode parsedResponse = graphQLTestUtils.parse(response.getBody());
		System.out.println("parsedResponse:: " + parsedResponse);
	}
}
