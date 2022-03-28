package com.assignment.bitbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.bitbuy.entity.User;
import com.assignment.bitbuy.exception.UnauthorizedUserException;
import com.assignment.bitbuy.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	@Autowired
	UserService userService;

	@PostMapping(path = "/api/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity createUser(@RequestBody User user) {

		// using lombock for logging
		log.info("In create user method for userId " + user.getUserName() + " " + user.getPassword());
		User createdUser = null;
		try {
			createdUser = userService.createUser(user);

		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(createdUser, HttpStatus.CREATED);

	}

	@PostMapping(path = "/api/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity login(@RequestBody User user) {

		log.info("In login method" + user.getUserName() + " " + user.getPassword());
		try {
			if (userService.login(user)) {
				return new ResponseEntity("User login success", HttpStatus.OK);
			}

			else {
				return new ResponseEntity("User login failed", HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/api/users/{uuid}")
	public ResponseEntity<User> getUser(@PathVariable("uuid") Long id, @RequestHeader String userName) {

		User user = null;

		try {
			// userName is required to confirm access to the API
			// this is just for Demo to show validation b4 accessing the resource
			// can also be by calling the login API above
			// or using JWT (cookies). Authentication Manager etc
			if (null != userName && !userName.isEmpty()) {
				user = userService.getUser(id, userName);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}

			else {
				return new ResponseEntity("Forbidden ,username missing", HttpStatus.FORBIDDEN);
			}

		}

		catch (UnauthorizedUserException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/api/users/{uuid}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable("uuid") Long id, @RequestBody User user) {
		
		// Another approach :In this method updating the user by validating the credentials
		// using the login API of this Rest Controller
		// validation in Service Layer
		User updatedUser = null;
		if (null != user) {
			if (null!= user.getUserName()
					&& null != user.getPassword()) {
				try {
					updatedUser = userService.updateUser(id, user);
					return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

				} 
				
				catch (UnauthorizedUserException e) {
					return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
				}
				catch (Exception e) {
					return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
				}

			}

			else {
				return new ResponseEntity("Username/Password missing in the body", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity("Username/Password missing in the body", HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/hello")
	public String testApp() {
		return "Bitbuy App is up";
	}
}