
package com.spring.rest_api.career_crafter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Application;
import com.spring.rest_api.career_crafter.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	public Application applyJob(Application application) {
		return applicationRepository.save(application);
	}

	public List<Application> getAllApplication() {
		return applicationRepository.findAll();
	}

}
