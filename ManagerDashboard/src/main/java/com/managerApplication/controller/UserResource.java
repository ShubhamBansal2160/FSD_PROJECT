package com.managerApplication.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.managerApplication.model.User;
import com.managerApplication.service.UserService;


@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User User) throws URISyntaxException {
		try {
			User result = userService.save(User);
			return ResponseEntity.created(new URI("/api/users/" + result.getUser_id())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}

//	@RequestMapping(value = "users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<User> updateUser(@RequestBody User users) throws URISyntaxException {
//		if (users.getUser_id() == 0) {
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//
//		try {
//			User result = usersService. update(users);
//
//			return ResponseEntity.created(new URI("/api/users/" + result.getUser_id())).body(result);
//		} catch (EntityNotFoundException e) {
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//	}

	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteUser(@PathVariable int user_id) {
		userService.delete(user_id);

		return ResponseEntity.ok().build();
	}
}
