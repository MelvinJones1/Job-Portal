package com.spring.rest_api.career_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.repository.AssessmentRepository;

@Service
public class AssessmentService {
	
	@Autowired
	private AssessmentRepository assessmentRepository;
}
