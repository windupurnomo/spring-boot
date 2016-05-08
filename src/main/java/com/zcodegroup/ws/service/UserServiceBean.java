package com.zcodegroup.ws.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zcodegroup.ws.model.User;

@Service
public class UserServiceBean implements UserService {

	private static BigInteger nextId;
	private static Map<BigInteger, User> users;

	private static User save(User user) {

		if (users == null) {
			users = new HashMap<BigInteger, User>();
			nextId = BigInteger.ONE;
		}

		// if update...
		if (user.getId() != null) {
			User oldUser = users.get(user.getId());
			if (oldUser == null) {
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

	private static boolean remove(BigInteger id) {
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

	@Override
	public Collection<User> findAll() {
		Collection<User> u = users.values();
		return u;
	}

	@Override
	public User findOne(BigInteger id) {
		User user = users.get(id);
		return user;
	}

	@Override
	public User create(User user) {
		User createdUser = save(user);
		return createdUser;
	}

	@Override
	public User update(User user) {
		User updatedUser = save(user);
		return updatedUser;
	}

	@Override
	public boolean delete(BigInteger id) {
		return remove(id);
	}

}
