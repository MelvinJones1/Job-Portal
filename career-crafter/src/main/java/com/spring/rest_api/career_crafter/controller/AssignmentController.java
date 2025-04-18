
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Assignment;



import com.spring.rest_api.career_crafter.service.AssignmentService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private MessageResponseDto messageDto;
    Logger logger =  LoggerFactory.getLogger("AssignmentController"); 

    @PostMapping("/add")
    public ResponseEntity<?> createAssignment(@RequestBody Assignment assignment) {
    	logger.info("Attempting to add new assignment: {}", assignment);	
        try {
        	//
            Assignment saved = assignmentService.addAssignment(assignment);
            logger.info("Assignment added successfully: {}", saved);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
        	logger.error("Error adding the assignment"+e.getMessage());
            messageDto.setMessage("Failed to add assignment: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
        
    }
    
    @GetMapping
    public ResponseEntity<?> getAllAssignments(@RequestParam int page,
            @RequestParam int size) {
        logger.info("Fetching all assignments with pagination");
        Pageable pageable = PageRequest.of(page, size); // Pagination object
        try {
            List<Assignment> assignments = assignmentService.getAllAssignments(pageable).getContent(); // Paginated content
            logger.info("Found assignments with pagination");
            return ResponseEntity.ok(assignments); // Return as a list
        } catch (Exception e) {
            logger.error("Error fetching assignments: {}", e.getMessage());
            MessageResponseDto messageDto = new MessageResponseDto();
            messageDto.setMessage("Failed to fetch assignments: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto); // Error response
        }
    }
    
	   
    
    
    //get assignment by assignnment id
    @GetMapping("/get/{aId}")
	public Assignment getSingleAssignment(@PathVariable int aId) {
    	logger.info("getting the assignment with id");
		return assignmentService.getSingleAssignment(aId);
		
		
	}
    
    //update assignment
 
  	@PutMapping("/update/{aId}")
  	public ResponseEntity<?> updateTheAssignment(@PathVariable int aId,
              @RequestBody Assignment updateAssignment)
  	
  	{
  		logger.info("updating the assignment with id");
  try {
  Assignment assignment = assignmentService.updateTheAssignment(aId, updateAssignment);
  logger.info("updated the assignment successfully");
  return ResponseEntity.ok(assignment);
  } catch (InvalidIDException e) {
	  logger.error("Failed to update assignment with ID");
	  messageDto.setMessage(e.getMessage());
	  messageDto.setStatus(400);
  return ResponseEntity.status(400).body(messageDto);
  } 
  }
  	//delete the assignment

  	@DeleteMapping("/delete/{aId}")
    public ResponseEntity<?> DeleteAssignmentById(@PathVariable int aId) throws InvalidIDException {
        logger.info("Attempting to delete assignment with ID: {}", aId);
        // Validate the ID and fetch the assignment
		Assignment assignment = assignmentService.getSingleAssignment(aId);
		if (assignment == null) {
		    logger.warn("No assignment found to delete with ID: {}", aId);
		    messageDto.setMessage("Assignment not found");
		    messageDto.setStatus(404);
		    return ResponseEntity.status(404).body(messageDto);
		}

		// Proceed with deletion
		assignmentService.DeleteAssignmentById(assignment);
		logger.info("Assignment with ID: {} deleted successfully", aId);
		messageDto.setMessage("Assignment record hard deleted from DB!!");
		messageDto.setStatus(200);
		return ResponseEntity.ok(messageDto);
    }
  	
}
