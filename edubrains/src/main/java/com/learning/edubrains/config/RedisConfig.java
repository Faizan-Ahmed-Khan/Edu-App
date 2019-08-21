package com.learning.edubrains.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	/*
	 * Define a RedisTemplate using the jedisConnectionFactory. This can be used for
	 * querying data with a custom repository.
	 * 
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> rt = new RedisTemplate<>();
		rt.setConnectionFactory(jedisConnectionFactory());
		return rt;
	}

	/*
	 * Define ConnectionFactory
	 * 
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jf = new JedisConnectionFactory();
		return jf;
	}
}
