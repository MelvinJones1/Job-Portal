package com.spring.rest_api.career_crafter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Application;
import com.spring.rest_api.career_crafter.model.Executive;
import com.spring.rest_api.career_crafter.model.Interview;
import com.spring.rest_api.career_crafter.repository.InterviewRepository;

@Service
public class InterviewService {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ExecutiveService executiveService;

    @Autowired
    private InterviewRepository interviewRepository;

    public Interview scheduleInterview(int applicationId, int executiveId, Interview interview) throws InvalidIDException{
        Application application = applicationService.findById(applicationId);

        Executive executive = executiveService.findById(executiveId);

        interview.setApplication(application);
        interview.setExecutive(executive);

        return interviewRepository.save(interview);
    }

    public List<Interview> getInterviewsByExecutive(int executiveId) throws InvalidIDException {
      if( executiveService.findById(executiveId) == null) {
    	  throw new InvalidIDException("Executive Id is not valid");
      }

        return interviewRepository.findByExecutiveId(executiveId);
    }

	public Interview addFeedback(int interviewId, String feedback) {
		Interview interview = interviewRepository.findById(interviewId).get();
		interview.setFeedback(feedback);
		
		return interviewRepository.save(interview);
	}

	public List<Interview> getAllInterviews() {

			return interviewRepository.findAll();
	}
	
	public Interview findById(int interviewId) throws InvalidIDException {
		Optional<Interview> optional = interviewRepository.findById(interviewId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Invalid Interview Id");
		}
		
		return optional.get();
	}

	public Interview rescheduleInterview(int id, Interview updated) throws InvalidIDException {
	    Interview existing = findById(id); 

	    existing.setDate(updated.getDate());
	    existing.setTime(updated.getTime());

	    return interviewRepository.save(existing);
	}


}
