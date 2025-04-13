package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.Company;
import com.spring.rest_api.career_crafter.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/add")
	public Company addCompany(@RequestBody Company company) {
		return companyService.addCompany(company);
		
	}

}
