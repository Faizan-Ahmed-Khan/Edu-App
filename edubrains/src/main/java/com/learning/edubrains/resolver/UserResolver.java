package com.learning.edubrains.resolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learning.edubrains.model.SessionUser;
import com.learning.edubrains.model.User;
import com.learning.edubrains.repo.ISessionUserRepo;
import com.learning.edubrains.service.IUserService;
import com.learning.edubrains.utils.ValdatorUtils;

import io.leangen.graphql.annotations.GraphQLQuery;

@Component
public class UserResolver {

	private Logger log = LogManager.getLogger(UserResolver.class);

	@Autowired
	private ISessionUserRepo repo;

	@Autowired
	private IUserService usrService;

	@Autowired
	private ValdatorUtils validator;

	@GraphQLQuery(name = "LOGIN")
	public SessionUser login(String userName) throws Exception {
		log.info("Login:: " + userName);
		if (validator.validateEmptyOrNull(userName)) {
			User u = usrService.getUser(userName);
			if (null == u)
				throw new Exception("User Does not Exist");
			// Persist the Session User in Redis
			SessionUser suser = new SessionUser(userName);
			repo.save(suser);
			return suser;
		} else
			throw new Exception("UserName cannot be null or Emplty");
	}
}
