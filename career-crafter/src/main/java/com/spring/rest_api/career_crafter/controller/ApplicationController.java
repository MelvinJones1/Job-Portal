package com.spring.rest_api.career_crafter.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Application;
import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.service.ApplicationService;
import com.spring.rest_api.career_crafter.service.JobSeekerService;
import com.spring.rest_api.career_crafter.service.JobService;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private JobService jobService;
	
	@PostMapping("/add/{jsId}/{jobId}")
	public Application applyJob(@PathVariable int jsId, @PathVariable int jobId
								,@RequestBody Application application) throws InvalidIDException {
		
		JobSeeker jobSeeker = jobSeekerService.getSingleJobSeeker(jsId);
		Job job =  jobService.getJobById(jobId);
		
		application.setJob(job);
		application.setJobSeeker(jobSeeker);
		application.setAppliedAt(LocalDate.now());
		
		return applicationService.applyJob(application);
		
	}
	
	@GetMapping("/all")
	public List<Application> getAllApplication() {
		return applicationService.getAllApplication();
	}
	
	

}
