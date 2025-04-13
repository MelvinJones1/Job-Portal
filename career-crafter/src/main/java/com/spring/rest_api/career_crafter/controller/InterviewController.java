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

	
    // Schedule an interview for a specific application and executive
    @PostMapping("/schedule/{applicationId}/{executiveId}")
    public Interview scheduleInterview(@PathVariable int applicationId,
                                       @PathVariable int executiveId,
                                       @RequestBody Interview interview) throws InvalidIDException{
        return interviewService.scheduleInterview(applicationId, executiveId, interview);
    }
    
    // Get all interviews assigned to a specific executive
    @GetMapping("/executive/{executiveId}")
    public List<Interview> getInterviewsForExecutive(@PathVariable int executiveId) throws InvalidIDException {
        return interviewService.getInterviewsByExecutive(executiveId);
    }
    
    // Add feedback for a particular interview
    @PutMapping("/addFeedback/{interviewId}")
    public Interview addFeedback(@PathVariable int interviewId,@RequestParam String feedback){
    	return interviewService.addFeedback(interviewId,feedback);
    }
    
    // Get all interviews
    @GetMapping("/all")
    public List<Interview> getAllInterviews(){
    	return interviewService.getAllInterviews();
    }
    
    // Reschedule an interview with new date/time
    @PutMapping("/reschedule/{interviewId}")
    public Interview rescheduleInterview(@PathVariable int interviewId, @RequestBody Interview updated) throws InvalidIDException {
        return interviewService.rescheduleInterview(interviewId, updated);
    }

    


}
