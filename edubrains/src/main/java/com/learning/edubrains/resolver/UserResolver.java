package com.learning.edubrains.resolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import io.leangen.graphql.annotations.GraphQLQuery;

@Component
public class UserResolver {

	private Logger log = LogManager.getLogger(UserResolver.class);

	@GraphQLQuery(name = "LOGIN")
	public String login(String name) {
		log.info("Login ");
		return "Hi " + name;
	}

}
