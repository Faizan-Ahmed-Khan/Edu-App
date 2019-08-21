package com.learning.edubrains.resolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.learning.edubrains.model.SessionUser;
import com.learning.edubrains.model.User;
import com.learning.edubrains.service.IUserService;
import com.learning.edubrains.utils.EduAppServiceException;
import com.learning.edubrains.utils.ValdatorUtils;

import io.leangen.graphql.annotations.GraphQLQuery;

@Component
public class UserResolver {

	private Logger log = LogManager.getLogger(UserResolver.class);

//	@Autowired
//	private ISessionUserRepo repo;

	@Autowired
	private IUserService usrService;

	@Autowired
	private ValdatorUtils validator;

//	@GraphQLQuery(name = "LOGIN")
//	public SessionUser login(String userName) throws Exception {
//		log.info("Login:: " + userName);
//		if (validator.validateEmptyOrNull(userName)) {
//			User u = usrService.getUser(userName);
//			if (null == u)
//				throw new Exception("User Does not Exist");
//			// Persist the Session User in Redis
//			SessionUser suser = new SessionUser(userName);
//			repo.save(suser);
//			return suser;
//		} else
//			throw new Exception("UserName cannot be null or Emplty");
//	}

	@GraphQLQuery(name = "LOGIN")
	public String login(String userName) throws Exception {
		log.info("Login:: " + userName);
		User u = null;
		if (validator.validateEmptyOrNull(userName)) {
			u = usrService.getUser(userName);
			if (null == u)
				throw new EduAppServiceException("User Does not Exist");
		}
		return createSession(userName);
	}

	@GraphQLQuery(name = "LOGOUT")
	public String logout() {
		log.info("Logout");
		createSession(null);
		return "Logged out Successfully";

	}

	private String createSession(String userName) {
		SessionUser sessionUser = new SessionUser(userName);
		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(sessionUser, null, null));
		return "User Logged In Successfully";
	}
}
