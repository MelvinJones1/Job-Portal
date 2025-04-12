package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.Executive;
import com.spring.rest_api.career_crafter.service.ExecutiveService;

@RestController
@RequestMapping("/api/executive")
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/add")
	public Executive addExecutive(@RequestBody Executive executive) {
		return executiveService.addExecutice(executive);
	}
	

}
