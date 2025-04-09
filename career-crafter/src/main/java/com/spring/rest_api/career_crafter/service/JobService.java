package com.spring.rest_api.career_crafter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;
	
	public List<Job> getAllJob() {
		return jobRepository.findAll();
	}

	public Job getSingleJobBy(int jobId) {
		Optional<Job> optional = jobRepository.findById(jobId);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Job ID...");
		return optional.get();
	}

}
