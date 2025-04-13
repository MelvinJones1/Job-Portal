package com.spring.rest_api.career_crafter.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Hr;
import com.spring.rest_api.career_crafter.model.Interview;
import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.service.HrService;

@RestController
@RequestMapping("/api/hr")
public class HrController {
	
	
	@Autowired
	private HrService hrService;
	
	@PostMapping("/add/{userId}/{companyId}")
    public Hr createHr(@PathVariable int userId,
                       @PathVariable int companyId,
                       @RequestBody Hr hr)  throws InvalidIDException{
		
        return hrService.createHr(userId, companyId, hr);
        
    }
	
	
	@GetMapping("/total-jobs/{hrId}")
	public long getTotalJobsPosted(@PathVariable int hrId) throws InvalidIDException {
	    return hrService.getTotalJobsByHr(hrId);
	}
	
	
	@GetMapping("/total-hires/{hrId}")
	public long getTotalHiredCandidates(@PathVariable int hrId) throws InvalidIDException {
	    return hrService.getTotalHiredByHr(hrId);
	}
	
	
	@GetMapping("/recent-jobs/{hrId}")
	public List<Job> getRecentJobs(@PathVariable int hrId) throws InvalidIDException {
	    return hrService.getRecentJobs(hrId);
	}

	@GetMapping("/recent-interviews/{hrId}")
	public List<Interview> getRecentInterviews(@PathVariable int hrId) throws InvalidIDException {
	    return hrService.getRecentInterviews(hrId);
	}



	
	
	

}
