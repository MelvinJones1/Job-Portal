package com.spring.rest_api.career_crafter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.repository.JobSeekerRepository;

@Service
public class JobSeekerService {

	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	public JobSeeker addJobSeeker(JobSeeker jobSeeker) {
		// TODO Auto-generated method stub
		return jobSeekerRepository.save(jobSeeker);
	}

	public JobSeeker getSingleJobSeeker(int jsId) {
		Optional<JobSeeker> optional = jobSeekerRepository.findById(jsId);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid JobSeekerID...");
		return optional.get();
	}

}
