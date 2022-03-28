package com.assignment.bitbuy.service;

import com.assignment.bitbuy.entity.User;

public interface UserService {

	User createUser(User user) throws Exception ;
	
	boolean login(User user) throws Exception ;
	
	User getUser(Long id, String userName) throws Exception ;
	
	User updateUser(Long id, User user) throws Exception ;
	
	
}
