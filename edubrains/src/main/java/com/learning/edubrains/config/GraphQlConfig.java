package com.learning.edubrains.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.edubrains.resolver.UserResolver;

import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;

@Configuration
public class GraphQlConfig {

	@Autowired
	UserResolver userResolver;

	@Bean
	public GraphQLSchema generateSchema() {
		return new GraphQLSchemaGenerator()
//				.withBasePackages("com.learning.edubrains")
				.withOperationsFromSingleton(userResolver)
				.generate();

	}
}
