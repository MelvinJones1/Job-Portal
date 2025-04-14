package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.model.Preference;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.service.AuthService;
import com.spring.rest_api.career_crafter.service.JobSeekerService;
import com.spring.rest_api.career_crafter.service.PreferenceService;

@RestController
@RequestMapping("/api/job-seeker")
public class JobSeekerController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PreferenceService preferenceService;
	
	
	@PostMapping("/add/{userId}/{prefId}")
	public JobSeeker addJobSeeker(@PathVariable int userId, @PathVariable int prefId, @RequestBody JobSeeker jobSeeker) throws InvalidIDException {
		User user =  authService.findById(userId);
		Preference preference = preferenceService.findById(prefId);
		jobSeeker.setUser(user);
		jobSeeker.setPreference(preference);
		

		return jobSeekerService.addJobSeeker(jobSeeker);
	}
	  	
	@GetMapping("/get/{jsId}")
	public JobSeeker getSingleJobSeeker(@PathVariable int jsId) throws InvalidIDException {
		return jobSeekerService.getSingleJobSeeker(jsId);
	}

}
