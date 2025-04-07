package com.spring.rest_api.career_crafter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.User;



public interface AuthRepository  extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);
}