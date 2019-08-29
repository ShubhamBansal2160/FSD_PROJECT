package com.managerApplication.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managerApplication.model.User;
import com.managerApplication.repository.UserRepository;


@Service
public class UserService{

	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		if (user.getUser_id() != null && userRepository.exists(user.getUser_id() )) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(Integer user_id) {
		return userRepository.findOne(user_id);
	}
	
	public void delete(Integer user_id) {
		userRepository.delete(user_id);
	}
}
