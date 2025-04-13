package com.spring.rest_api.career_crafter.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Hr;
import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.service.ApplicationService;
import com.spring.rest_api.career_crafter.service.HrService;
import com.spring.rest_api.career_crafter.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController{

	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private HrService hrService;
	
	@Autowired
	private ApplicationService applicationService;

	
	@GetMapping("/all")
	public List<Job> getAllJob() {
		return jobService.getAllJob();
		
	}
	
	@PostMapping("/add/{hrId}")
	public Job postJob(@PathVariable int hrId, @RequestBody Job job) throws InvalidIDException{
	    // Ensure the HR exists
	    Hr hr = hrService.findById(hrId);
	            

	    job.setHr(hr); // Associate job with HR
	    return jobService.createJob(job);
	}
	
	@GetMapping("/hr/{hrId}")
	public List<Job> fetchJobsByHr(@PathVariable int hrId) throws InvalidIDException{
		
		if(hrService.findById(hrId)==null) {
			throw new InvalidIDException("Hr Id is invalid");
		}
		
		
	    return jobService.fetchJobsByHr(hrId);
	}
	
	
	@GetMapping("/{jobId}")
	public Job fetchJobById(@PathVariable int jobId) throws InvalidIDException {
		
	    return jobService.getJobById(jobId);
	    
	}
	
	
	@PutMapping("/update/{jobId}")
	public Job editJob(@PathVariable int jobId, @RequestBody Job updatedJob) throws InvalidIDException {
		
		
	    return jobService.updateJob(jobId, updatedJob);
	}
	
	
	@DeleteMapping("/delete/{jobId}")
	public String deleteJob(@PathVariable int jobId) throws InvalidIDException{
	    return jobService.removeJob(jobId);
	}
	
	@GetMapping("/{jobId}/applicant-count")
	public int getApplicantsCountForJob(@PathVariable int jobId) throws InvalidIDException{
	    return applicationService.countApplicantsForJob(jobId);
	}


}
