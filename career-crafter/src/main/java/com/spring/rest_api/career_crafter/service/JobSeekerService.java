package com.spring.rest_api.career_crafter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.model.Preference;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.JobSeekerRepository;

@Service
public class JobSeekerService {

	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PreferenceService preferenceService;
	
	
	

	public JobSeeker getSingleJobSeeker(int jsId) throws InvalidIDException{
		Optional<JobSeeker> optional = jobSeekerRepository.findById(jsId);
		if(optional.isEmpty())
			throw new InvalidIDException("Invalid JobSeekerID...");
		return optional.get();
	}

	public JobSeeker createJobSeeker(int userId, int preferenceId, JobSeeker jobSeeker) throws InvalidIDException{
	    User user = authService.findById(userId);

	    if (!user.getRole().equalsIgnoreCase("JOBSEEKER")) {
	        throw new RuntimeException("Invalid role for job seeker");
	    }

	    Preference preference = preferenceService.findById(preferenceId);

	    jobSeeker.setUser(user);
	    jobSeeker.setPreferences(preference);

	    return jobSeekerRepository.save(jobSeeker);
	}


}
