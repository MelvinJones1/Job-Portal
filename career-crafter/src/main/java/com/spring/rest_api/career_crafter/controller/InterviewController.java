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
import com.spring.rest_api.career_crafter.model.Interview;
import com.spring.rest_api.career_crafter.service.InterviewService;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {
	
	@Autowired
    private InterviewService interviewService;

    @PostMapping("/schedule/{applicationId}/{executiveId}")
    public Interview scheduleInterview(@PathVariable int applicationId,
                                       @PathVariable int executiveId,
                                       @RequestBody Interview interview) throws InvalidIDException{
        return interviewService.scheduleInterview(applicationId, executiveId, interview);
    }
    
    @GetMapping("/executive/{executiveId}")
    public List<Interview> getInterviewsForExecutive(@PathVariable int executiveId) throws InvalidIDException {
        return interviewService.getInterviewsByExecutive(executiveId);
    }
    
    @PutMapping("/addFeedback/{interviewId}")
    public Interview addFeedback(@PathVariable int interviewId,@RequestParam String feedback){
    	return interviewService.addFeedback(interviewId,feedback);
    }
    
    @GetMapping("/all")
    public List<Interview> getAllInterviews(){
    	return interviewService.getAllInterviews();
    }
    
    @PutMapping("/reschedule/{id}")
    public Interview rescheduleInterview(@PathVariable int id, @RequestBody Interview updated) throws InvalidIDException {
        return interviewService.rescheduleInterview(id, updated);
    }

    


}
