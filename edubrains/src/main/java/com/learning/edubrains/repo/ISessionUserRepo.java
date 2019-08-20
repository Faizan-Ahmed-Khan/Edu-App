package com.learning.edubrains.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.edubrains.model.SessionUser;

@Repository
public interface ISessionUserRepo extends CrudRepository<SessionUser, String> {

	SessionUser findByUserName(String userName);
}
