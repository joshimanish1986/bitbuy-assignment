package com.assignment.bitbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.bitbuy.entity.User;
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
			
			else{
				return new ResponseEntity("User login failed", HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/api/users/{uuid}")
	public ResponseEntity<User> getUser(@PathVariable("uuid") Long id) {

		User user = null;

		try {
			user = userService.getUser(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/api/users/{uuid}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable("uuid") Long id, @RequestBody User user) {

		User updatedUser = null;

		try {
			updatedUser = userService.updateUser(id, user);
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/*
	 * @PostMapping(path = "/api/login", consumes = "application/json", produces =
	 * "application/json") public ResponseEntity login(@RequestBody User user) {
	 * Authentication authentication = authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * //BitBuyUserDetailsService userDetails = (BitBuyUserDetailsService)
	 * authentication.getPrincipal(); ResponseCookie jwtCookie =
	 * jwtUtils.generateJwtCookie(user.getUsername()); return
	 * ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
	 * jwtCookie.toString()).body("Login success"); }
	 */

	@GetMapping("/hello")
	public String getSecret() {
		return "Hello App is up";
	}
}