package com.learning.edubrains.resolver;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GraphQLTestUtils {

	/**
	 * Where to send GraphQL requests
	 */
	public static String ENDPOINT_LOCATION = "/graphql";

	/**
	 * Basic empty GraphQL query.
	 */
	@Autowired
	private String queryWrapper;

	private JsonStringEncoder jsonStringEncoder = new JsonStringEncoder();

	/**
	 * Call this method with a valid GraphQL query/mutation String This function
	 * will escape it properly and wrap it into a JSON query object
	 */
	public String createJsonQuery(String graphQL) {
		// TODO add support for setting variables in the query
		return queryWrapper.replace("__payload__", escapeQuery(graphQL));
	}

	/**
	 * Clean the given QraphQL query so that it can be embedded in a JSON string.
	 */
	public String escapeQuery(String graphQL) {
		return new String(jsonStringEncoder.quoteAsString(graphQL));
	}

	/**
	 * Parse the given payload as a [JsonNode]
	 * 
	 * @return the payload parsed as JSON
	 * @throws IOException
	 */
	public JsonNode parse(String payload) throws IOException {
		return new ObjectMapper().readTree(payload);
	}
}
