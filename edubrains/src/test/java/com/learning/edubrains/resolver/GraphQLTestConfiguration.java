package com.learning.edubrains.resolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

@Configuration
public class GraphQLTestConfiguration {

//	@Value("classpath:src/test/resources/sign-in.graphql")
//	private Resource signInPayloadFile;

	@Bean
	public String signInPayload() throws IOException {
		return StreamUtils.copyToString(new ClassPathResource("sign-in.graphql").getInputStream(),
				StandardCharsets.UTF_8);
	}

	@Bean
	public String queryWrapper() throws IOException {
		return StreamUtils.copyToString(new ClassPathResource("query-wrapper.json").getInputStream(),
				StandardCharsets.UTF_8);
	}

}
