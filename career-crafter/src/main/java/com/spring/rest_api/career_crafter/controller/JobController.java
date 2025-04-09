package com.spring.rest_api.career_crafter.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController{
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/all")
	public List<Job> getAllJob() {
		return jobService.getAllJob();
		
	}

}
