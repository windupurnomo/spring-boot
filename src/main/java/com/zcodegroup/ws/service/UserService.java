package com.zcodegroup.ws.service;

import java.math.BigInteger;
import java.util.Collection;

import com.zcodegroup.ws.model.User;

public interface UserService {
	public Collection<User> findAll();
	public User findOne(BigInteger id);
	public User create(User user);
	public User update(User user);
	public boolean delete(BigInteger id);
}
