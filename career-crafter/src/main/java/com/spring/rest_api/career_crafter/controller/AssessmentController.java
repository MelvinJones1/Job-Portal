package com.spring.rest_api.career_crafter.controller;


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
import com.spring.rest_api.career_crafter.service.ApplicationService;
import com.spring.rest_api.career_crafter.service.AssessmentService;

@RestController
@RequestMapping("api/assessment")
public class AssessmentController {

	@Autowired
    private final ApplicationService applicationService;
	
	@Autowired
	private AssessmentService assessmentService;


    AssessmentController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
	
	
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
	
	
	//Returns list of assessment for the JobSeeker
	@GetMapping("/all/{appId}")
	public Assessment getAllAssessment(@PathVariable int appId) throws InvalidIDException {
		applicationService.getSingleApplicationId(appId);
		return assessmentService.getAllAssessment(appId);
	}
	
	


}
