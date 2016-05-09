package com.zcodegroup.ws.service;

import java.util.Collection;

import com.zcodegroup.ws.model.User;

public interface UserService {
	public Collection<User> findAll();
	public User findOne(Long id);
	public User create(User user);
	public User update(User user);
	public boolean delete(Long id);
}
