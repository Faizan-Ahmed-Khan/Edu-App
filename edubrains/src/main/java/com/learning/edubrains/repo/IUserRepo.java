package com.learning.edubrains.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learning.edubrains.model.User;

@Repository
public interface IUserRepo extends MongoRepository<User, String> {

	User findByUserName(String userName);

	User findByEmail(String email);
}
