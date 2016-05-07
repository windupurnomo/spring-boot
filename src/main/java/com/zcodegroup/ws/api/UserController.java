package com.zcodegroup.ws.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zcodegroup.ws.model.User;

@RestController
public class UserController {

	private static BigInteger nextId;
	private static Map<BigInteger, User> users;

	private static User save(User user) {

		if (users == null) {
			users = new HashMap<BigInteger, User>();
			nextId = BigInteger.ONE;
		}
		user.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		users.put(user.getId(), user);
		return user;
	}

	static {
		User u1 = new User();
		u1.setName("Windu Purnomo");
		save(u1);

		User u2 = new User();
		u2.setName("Zinedine Irhab Purnomo");
		save(u2);
	}

	@RequestMapping(value = "/api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		Collection<User> u = users.values();
		return new ResponseEntity<Collection<User>>(u, HttpStatus.OK);
	}
}
