package com.assignment.bitbuy.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.bitbuy.entity.User;
import com.assignment.bitbuy.exception.UnauthorizedUserException;
import com.assignment.bitbuy.repository.UserRepository;

import lombok.Lombok;
import lombok.SneakyThrows;

@Service
@Transactional
public class IUserService implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public User createUser(User user) throws Exception {
		// encrypt the password before saving in DB.
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		return userRepo.save(user);
	}

	@Override
	public boolean login(User user) throws Exception {

		User dbUSer = null;
		dbUSer = userRepo.findByUserName(user.getUserName());

		if (null != dbUSer) {
			if (BCrypt.checkpw(user.getPassword(), dbUSer.getPassword()))
				return true;

		} else {
			throw new Exception("User " + user.getUserName() + " not found ");
		}
		return false;

	}

	@Override
	public User getUser(Long id, String userName) throws UnauthorizedUserException, Exception {

		User dbUser = null;
		if (null != userRepo.findByUserName(userName)) {
			try {
				dbUser = userRepo.findById(id).get();
				return dbUser;
			} catch (NoSuchElementException e) {
				throw new Exception("User not found for id " + id);
			}

		}
		else {
			throw new UnauthorizedUserException("Access Denied for user :: " + userName);
		}

	}

	@Override
	public User updateUser(Long id, User user) throws Exception {
		User dbUser = null;

		dbUser = userRepo.findById(id).map(u -> {
			try {
				if(login(user)) { // using inbuilt login method for validation
					u.setName(u.getName());
					u.setEmail(user.getEmail());
					u.setPhone(user.getPhone());
					
				}
				
				else {
					throw new UnauthorizedUserException("Access Denied for user :: " + user.getUserName());
				}
			} catch (Exception e) {
				Lombok.sneakyThrow(e); //Using lombock to throw the exception
			}
			return u;
			
		}).orElseThrow(() -> new Exception("User not found for id " + id));

		// update the user and return.
		return userRepo.save(dbUser);

	}

}
