package com.spring.rest_api.career_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Executive;
import com.spring.rest_api.career_crafter.repository.ExecutiveRepository;

@Service
public class ExecutiveService {
	
	@Autowired
	private ExecutiveRepository executiveRepository;

	public Executive addExecutice(Executive executive) {
		return executiveRepository.save(executive);
	}

}
