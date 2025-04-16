package com.spring.rest_api.career_crafter.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.enums.ApplicationStatus;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Application;
import com.spring.rest_api.career_crafter.model.Job;
import com.spring.rest_api.career_crafter.model.JobSeeker;
import com.spring.rest_api.career_crafter.service.ApplicationService;
import com.spring.rest_api.career_crafter.service.AssessmentService;
import com.spring.rest_api.career_crafter.service.JobSeekerService;
import com.spring.rest_api.career_crafter.service.JobService;

@RestController    
@RequestMapping("/api/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private AssessmentService assessmentService;
	
    // Job Seeker applies to a job using job ID and their ID
	@PostMapping("/add/{jsId}/{jobId}")
	public Application applyJob(@PathVariable int jsId, @PathVariable int jobId
								,@RequestBody Application application) throws InvalidIDException {
		
		JobSeeker jobSeeker = jobSeekerService.getSingleJobSeeker(jsId);
		Job job =  jobService.getJobById(jobId);
		
		application.setJob(job);
		application.setJobSeeker(jobSeeker);
		application.setStatus(ApplicationStatus.APPLIED);
		application.setAppliedAt(LocalDate.now());
		
		return applicationService.applyJob(application);
		
	}
	
    // Get list of all job applications.
	@GetMapping("/all")
	public List<Application> getAllApplication() {
		return applicationService.getAllApplication();
	}
	
    // Get applications for a specific job by job ID
	@GetMapping("/job/{jobId}/applications")
    public List<Application> getApplicationsByJob(@PathVariable int jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }
	
    // Update status of an application (e.g., Shortlisted, Hired, Rejected)
	@PutMapping("/update-status/{applicationId}")
	public Application updateStatus(@PathVariable int applicationId, @RequestParam String status) throws InvalidIDException {
	    return applicationService.updateApplicationStatus(applicationId, status);
	}
	
    // Get all applications sorted by assessment score in descending order
	@GetMapping("/sort-by-score")
	public List<Application> getSortedApplicationsByScore() {
	    return assessmentService.getApplicationsSortedByScoreDesc();  
	}

	// Get Total No. of applications submitted by Jobseeker
	@GetMapping("/count/total/{jsId}")
	public int getTotalApplications(@PathVariable int jsId) throws InvalidIDException {
		jobSeekerService.getSingleJobSeeker(jsId);
		return applicationService.getTotalApplications(jsId);
		}

	// Get Total No. of applications based on status of application 
	@GetMapping("/count-by-status/{jsId}")
	public int getApplicationCountByStatus(@PathVariable int jsId, @RequestParam ApplicationStatus status) throws InvalidIDException {
		jobSeekerService.getSingleJobSeeker(jsId);
		return applicationService.getApplicationCountByStatus(status);
	}
	
	
	
}
