package com.spring.rest_api.career_crafter.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.Instructor;
import com.spring.rest_api.career_crafter.service.InstructorService;

@RestController
@RequestMapping("/api/instructor/profile")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	//get instructor prfile
	@GetMapping("/getdetails")
	public Instructor getInstructorProfile(Principal principal)

	{
		String username=principal.getName();
		return instructorService.getInstructorProfile(username);
	
	}
	
	//cretae profiel
	@PostMapping("/createprofile")
	 public Instructor createInstructorProfile(@RequestBody Instructor instructor, Principal principal) {
	        String username = principal.getName();
	        return instructorService.createInstructorProfile(instructor, username);
	    }
}
