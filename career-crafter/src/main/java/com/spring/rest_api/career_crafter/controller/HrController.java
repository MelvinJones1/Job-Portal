package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.service.HrService;

@RestController
@RequestMapping("/api/hr")
public class HrController {
	
	
	@Autowired
	private HrService hrService;
	
	
	

}
