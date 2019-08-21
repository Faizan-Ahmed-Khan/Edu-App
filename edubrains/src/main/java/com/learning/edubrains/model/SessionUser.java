package com.learning.edubrains.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("SessionUser")
@Data
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;

	// Needed for REDIS impl
	private String id;

	private String userName;

	public SessionUser(String userName) {
		this.userName = userName;
	}

}
