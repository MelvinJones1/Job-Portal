package com.spring.rest_api.career_crafter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Application;
import com.spring.rest_api.career_crafter.model.Assessment;
import com.spring.rest_api.career_crafter.repository.AssessmentRepository;

@Service
public class AssessmentService {
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	@Autowired
	private ApplicationService  applicationService;

	public Assessment sendAssessment(int applicationId, Assessment assessment) throws InvalidIDException{
	    Application application = applicationService.findById(applicationId);

	    assessment.setApplication(application);
	    assessment.setCompleted(false);  // Initially false
	    assessment.setScore(null);       // No score yet

	    return assessmentRepository.save(assessment);
	}

	public List<Application> getApplicationsSortedByScoreDesc() {
	    List<Assessment> assessments = assessmentRepository.findAll();

	    return assessments.stream()
	        .filter(a -> a.getScore() != null)
	        .sorted((a1, a2) -> Double.compare(a2.getScore(), a1.getScore())) // High to Low
	        .map(Assessment::getApplication)
	        .toList();
	}

	public Assessment updateScore(int assessmentId, double score) {
        Assessment assessment = assessmentRepository.findById(assessmentId).get();

        assessment.setScore(score);
        assessment.setCompleted(true); 

        return assessmentRepository.save(assessment);
    }


	public Assessment getAllAssessment(int appId) {
			return assessmentRepository.findByApplicationId(appId);
	}


}
