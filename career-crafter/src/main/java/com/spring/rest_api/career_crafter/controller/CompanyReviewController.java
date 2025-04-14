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
import com.spring.rest_api.career_crafter.model.Company;
import com.spring.rest_api.career_crafter.model.CompanyReview;
import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.service.CompanyReviewService;
import com.spring.rest_api.career_crafter.service.CompanyService;
import com.spring.rest_api.career_crafter.service.JobSeekerService;

@RestController
@RequestMapping("/api/company-review")
public class CompanyReviewController {
	
	@Autowired
	private CompanyReviewService companyReviewService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	
	//Post company review
	@PostMapping("/add/{jsId}/{compId}")
	public CompanyReview postReview(@PathVariable int jsId, @PathVariable int compId, 
									@RequestBody CompanyReview companyReview)  throws InvalidIDException{
		
		JobSeeker jobSeeker = jobSeekerService.getSingleJobSeeker(jsId);
		Company company = companyService.getSingleCompany(compId);
		
		companyReview.setDatePosted(LocalDate.now());
		companyReview.setJobSeeker(jobSeeker);
		companyReview.setCompany(company);
		
		return companyReviewService.postReview(companyReview);
	}
	
	//get all reviews of a company
	@GetMapping("/get-by-company/{compId}")
	public List<CompanyReview> getCompanyReviewByCompId(@PathVariable int compId) {
		companyService.getSingleCompany(compId);
		return companyReviewService.getCompanyReviewByCompId(compId);
	}

	//gets the overall rating of a company
	@GetMapping("/overall-rating/{compId}")
	public Double getOverallRating(@PathVariable int compId) {
		companyService.getSingleCompany(compId);
		return companyReviewService.getOverallRating(compId);
	}
	
	
	@GetMapping("/count/{compId}")
	public int getTotalReviews(@PathVariable int compId) {
		companyService.getSingleCompany(compId);
		return companyReviewService.getTotalReviews(compId);
	}
}
