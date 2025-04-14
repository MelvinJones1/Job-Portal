package com.spring.rest_api.career_crafter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.CompanyReview;
import com.spring.rest_api.career_crafter.repository.CompanyReviewRepository;

@Service
public class CompanyReviewService {

	@Autowired
	private CompanyReviewRepository companyReviewRepository;
	
	//post review for a company
	public CompanyReview postReview(CompanyReview companyReview) {
		return companyReviewRepository.save(companyReview);
	}

	//list of all reviews for a company
	public List<CompanyReview> getCompanyReviewByCompId(int compId) {
		return companyReviewRepository.findAllByCompanyId(compId);
	}
	
	//returns total no. of reviews for a company
	public int getTotalReviews(int compId) {
		return companyReviewRepository.findAllByCompanyId(compId).size();
	}

	//Returns Overall Rating of a company
	public Double getOverallRating(int compId) {
		double sum=0;
		int size = companyReviewRepository.findAllByCompanyId(compId).size();
		
		for (CompanyReview c : getCompanyReviewByCompId(compId)) {
			sum += c.getRating();
		}
		
		double overallRating = sum/size;
		return overallRating;
	}

	
	

}
