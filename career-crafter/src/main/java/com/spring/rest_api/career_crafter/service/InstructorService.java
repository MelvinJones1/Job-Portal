package com.spring.rest_api.career_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Instructor;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.AuthRepository;
import com.spring.rest_api.career_crafter.repository.InstructorRepository;
@Service
public class InstructorService {

	@Autowired 
	private AuthRepository authRepository;
	@Autowired 
	private InstructorRepository instructorRepository;
	
	
	//get the instructor profile
	public Instructor getInstructorProfile(String username)
	{
		
		User user = authRepository.findByUsername(username);
	        return instructorRepository.findByUser(user);
	}
	
	//save rhe instructor prifle

    public Instructor createInstructorProfile(Instructor instructor, String username) {
        User user = authRepository.findByUsername(username);
        instructor.setUser(user);
        return instructorRepository.save(instructor);
    }

}