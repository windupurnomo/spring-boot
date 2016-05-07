package com.zcodegroup.ws.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		// if update...
		if (user.getId() != null){
			User oldUser = users.get(user.getId());
			if (oldUser == null){
				return null;
			}
			users.remove(user.getId());
			users.put(user.getId(), user);
			return user;
		}
		
		// if create ...
		user.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		users.put(user.getId(), user);
		return user;
	}

	private static boolean delete(BigInteger id){
		User deletedUser = users.remove(id);
		return deletedUser != null;
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

	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") BigInteger id){
		User user = users.get(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = save(user);
		return new ResponseEntity<User>(createdUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User updatedUser = save(user);
		if (updatedUser == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") BigInteger id){
		boolean isDeleted = delete(id);
		if (!isDeleted){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	
	
	
	
	
	
	


}
