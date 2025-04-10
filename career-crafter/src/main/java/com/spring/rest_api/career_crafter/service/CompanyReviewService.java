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
	
	public CompanyReview postReview(CompanyReview companyReview) {
		return companyReviewRepository.save(companyReview);
	}

	public List<CompanyReview> getCompanyReviewByCompId(int compId) {
		
		return companyReviewRepository.findAllByCompanyId(compId);
	}

}
