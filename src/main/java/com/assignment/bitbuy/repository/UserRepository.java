package com.assignment.bitbuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.bitbuy.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
	
}