package com.learning.edubrains.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.edubrains.model.Roles;

public interface IRolesRepo extends MongoRepository<Roles, String> {

	Roles findByRoleName(String roleName);
}
