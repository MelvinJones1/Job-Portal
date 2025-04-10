package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.service.JobSeekerService;

@RestController
@RequestMapping("/api/job-seeker")
public class JobSeekerController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@PostMapping("/add")
	public JobSeeker addJobSeeker(@RequestBody JobSeeker jobSeeker) {
		return jobSeekerService.addJobSeeker(jobSeeker);
	}
	
	@GetMapping("/get/{jsId}")
	public JobSeeker getSingleJobSeeker(@PathVariable int jsId) {
		return jobSeekerService.getSingleJobSeeker(jsId);
	}

}
