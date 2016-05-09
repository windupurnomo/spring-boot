package com.zcodegroup.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcodegroup.ws.model.User;
import com.zcodegroup.ws.repository.UserRepository;

@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		Collection<User> u = userRepository.findAll();
		return u;
	}

	@Override
	public User findOne(Long id) {
		User user = userRepository.findOne(id);
		return user;
	}

	@Override
	public User create(User user) {
		if (user.getId() != null){
			return null;
		}
		User createdUser = userRepository.save(user);
		return createdUser;
	}

	@Override
	public User update(User user) {
		User tempUser = userRepository.findOne(user.getId());
		if (tempUser == null){
			//user not found
			return null;
		}
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@Override
	public boolean delete(Long id) {
		userRepository.delete(id);
		return true;
	}

}
