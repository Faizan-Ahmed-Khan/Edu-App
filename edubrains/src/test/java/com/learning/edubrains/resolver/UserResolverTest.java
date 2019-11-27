package com.learning.edubrains.resolver;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.learning.edubrains.repo.IUserRepo;

@RunWith(SpringRunner.class)
//@GraphQLTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResolverTest {

	@LocalServerPort
	private int port;

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

	@Autowired
	private IUserRepo repo;

	@Autowired
	private String signInPayload;

	@Autowired
	GraphQLTestUtilss graphQLTestUtils;

	@Test
	public void testSignIn() throws IOException {

//		String payload = graphQLTestUtils.createJsonQuery(signInPayload);
		System.out.println("signInPayload:: " + signInPayload);

		// create the JSON version of query, that will be added to the body of request
		String payload = graphQLTestUtils.createJsonQuery(signInPayload);
		System.out.println("payload:: " + payload);

		GraphQLResponse response = graphQLTestTemplate.postMultipart(payload, "{}");
		System.out.println(response);
	}

}
