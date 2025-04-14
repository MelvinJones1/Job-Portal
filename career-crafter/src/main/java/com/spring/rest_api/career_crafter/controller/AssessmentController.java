package com.spring.rest_api.career_crafter.controller;


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

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Assessment;
import com.spring.rest_api.career_crafter.service.AssessmentService;
import com.spring.rest_api.career_crafter.service.JobSeekerService;

@RestController
@RequestMapping("api/assessment")
public class AssessmentController {

	@Autowired
    private JobSeekerService jobSeekerService;
	
	@Autowired
	private AssessmentService assessmentService;


   
    // Send an assessment to a candidate for a specific application
	@PostMapping("/send/{applicationId}")
	public Assessment sendAssessment(@PathVariable int applicationId,
	                                 @RequestBody Assessment assessment)  throws InvalidIDException{
	    return assessmentService.sendAssessment(applicationId, assessment);
	}
	
	
    // Update the score of an existing assessment
	@PutMapping("/update-score/{assessmentId}")
    public Assessment updateScore(@PathVariable int assessmentId,
                                  @RequestParam double score) {
        return assessmentService.updateScore(assessmentId, score);
    }
	
	
	//Returns list of all assessments
	@GetMapping("/all")
	public List<Assessment> getAllAssessment(){
		return assessmentService.getAllAssessment();
	}
	
	//returns list of all assessments assigned to a jobseeker
	@GetMapping("/all/{jsId}")
	public List<Assessment> getAssessmentByJobSeekerId(@PathVariable int jsId) throws InvalidIDException {
		jobSeekerService.getSingleJobSeeker(jsId);
		return assessmentService.getAssessmentByJobSeekerId(jsId);
	}

}
