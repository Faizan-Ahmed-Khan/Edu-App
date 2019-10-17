package com.learning.edubrains.resolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.learning.edubrains.model.AuthData;
import com.learning.edubrains.model.Roles;
import com.learning.edubrains.model.SessionUser;
import com.learning.edubrains.model.SigninPayload;
import com.learning.edubrains.model.User;
import com.learning.edubrains.service.IUserService;
import com.learning.edubrains.utils.EduAppServiceException;
import com.learning.edubrains.utils.ValdatorUtils;

import graphql.GraphQLException;
import io.leangen.graphql.annotations.GraphQLMutation;
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

	@GraphQLMutation(name = "LOGIN")
	public SigninPayload signIn(AuthData auth) throws IllegalAccessException {
		log.info("Login, payload:: " + auth.toString());
		if (!validator.validateEmptyOrNull(auth.getEmail())) {
			throw new EduAppServiceException("Email Cannot be Empty/null");
		} else if (!validator.validateEmptyOrNull(auth.getPassword())) {
			throw new EduAppServiceException("Password Cannot be Empty/null");
		}

		User user = usrService.getUserByEmail(auth.getEmail());
		if (null == user)
			throw new EduAppServiceException("User Does not Exist");
		else if (user.getPwd().equals(auth.getPassword())) {
//			//Persist the Session User in Redis
//			SessionUser suser = new SessionUser(userName);
//			repo.save(suser);
//			return suser;

			// Use Spring Security to Maintain Session
			createSession(user.getUserName());
			return new SigninPayload(user.getId(), user);
		} else
			throw new GraphQLException("Invalid Credentials");
	}

	@GraphQLQuery(name = "LOGOUT")
	public String logout() {
		log.info("Logout");
		createSession(null);
		return "Logged out Successfully";

	}

	@GraphQLMutation
	public String createUser(String name, AuthData auth, Roles role) {
		User newUser = new User(name, role, auth.getEmail(), auth.getPassword());
		usrService.addUser(newUser);
		return "User Added Successfully";
	}

	private String createSession(String userName) {
		SessionUser sessionUser = new SessionUser(userName);
		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(sessionUser, null, null));
		return "User Logged In Successfully";
	}
}
