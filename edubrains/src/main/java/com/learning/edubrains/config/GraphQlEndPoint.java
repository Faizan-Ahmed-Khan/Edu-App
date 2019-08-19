package com.learning.edubrains.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.servlet.AbstractGraphQLHttpServlet;
import graphql.servlet.GraphQLInvocationInputFactory;
import graphql.servlet.GraphQLObjectMapper;
import graphql.servlet.GraphQLQueryInvoker;
import graphql.servlet.GraphQLSingleInvocationInput;
import graphql.servlet.internal.GraphQLRequest;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/graphql")
public class GraphQlEndPoint extends AbstractGraphQLHttpServlet {

	private GraphQLSchema schemaConf;

	public GraphQlEndPoint(GraphQLSchema schema) {
		this.schemaConf = schema;
		GraphQL.newGraphQL(schemaConf).build();
	}

	// build new GraphQLQueryInvoker
	@Override
	protected GraphQLQueryInvoker getQueryInvoker() {
		return GraphQLQueryInvoker.newBuilder().build();
	}

	// build new GraphQLInvocationInputFactory
	@Override
	protected GraphQLInvocationInputFactory getInvocationInputFactory() {
		return GraphQLInvocationInputFactory.newBuilder(schemaConf).build();
	}

	// build new GraphQLObjectMapper
	@Override
	protected GraphQLObjectMapper getGraphQLObjectMapper() {
		return GraphQLObjectMapper.newBuilder().build();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("-----------------Inside doPOST-----------------");
		GraphQLInvocationInputFactory invocationInputFactory = getInvocationInputFactory();
		GraphQLObjectMapper graphQLObjectMapper = getGraphQLObjectMapper();
		GraphQLQueryInvoker queryInvoker = getQueryInvoker();
		try {
			InputStream inputStream = req.getInputStream();
			if (!inputStream.markSupported()) {
				inputStream = new BufferedInputStream(inputStream);
			}

			GraphQLRequest gReq = graphQLObjectMapper.readGraphQLRequest(inputStream);

			GraphQLSingleInvocationInput invocInput = invocationInputFactory.create(gReq, req);
			query(queryInvoker, graphQLObjectMapper, invocInput, resp);
			log.info("-----------------END doPOST-----------------");
		} catch (Exception e) {
			log.info("Bad GRAPHQL request: parsing failed", e);
			resp.setStatus(STATUS_BAD_REQUEST);
		}
	}

	private void query(GraphQLQueryInvoker queryInvoker, GraphQLObjectMapper graphQLObjectMapper,
			GraphQLSingleInvocationInput invocationInput, HttpServletResponse resp) throws IOException {
		log.info("-----------------Inside query-----------------");
		ExecutionResult result = queryInvoker.query(invocationInput);
		log.info("Result:: " + result);
		resp.setContentType(APPLICATION_JSON_UTF8);
		resp.setStatus(STATUS_BAD_REQUEST);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		resp.getWriter().write(graphQLObjectMapper.serializeResultAsJson(result));
		log.info("-----------------END QUErY-----------------");
	}
}
