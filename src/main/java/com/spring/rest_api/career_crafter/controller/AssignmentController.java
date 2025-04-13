package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Assignment;


import com.spring.rest_api.career_crafter.service.AssignmentService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;

import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity<?> createAssignment(@RequestBody Assignment assignment) {
        try {
            Assignment saved = assignmentService.addAssignment(assignment);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            messageDto.setMessage("Failed to add assignment: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
        
    }
    
    //get all assignments
    @GetMapping
    public ResponseEntity<?> getAllAssignments() {
        List<Assignment> list = assignmentService.getAllAssignments();
        return ResponseEntity.ok(list);
    }
    //get assignment by id
    @GetMapping("/get/{aId}")
	public Assignment getSingleAssignment(@PathVariable int aId) {
		return assignmentService.getSingleAssignment(aId);
		
		
	}
    
    //update assignment
 
  	@PutMapping("/update/{aId}")
  	public ResponseEntity<?> updateTheAssignment(@PathVariable int aId,
              @RequestBody Assignment updateAssignment) {
  try {
  Assignment assignment = assignmentService.updateTheAssignment(aId, updateAssignment);
  return ResponseEntity.ok(assignment);
  } catch (InvalidIDException e) {

	  messageDto.setMessage(e.getMessage());
	  messageDto.setStatus(400);
  return ResponseEntity.status(400).body(messageDto);
  } 
  }
  	//delete the assignment

  	@DeleteMapping("/delete/{aId}")
  	public ResponseEntity<?> DeleteAssignmentById(@PathVariable int aId) throws InvalidIDException {
  		//lets validate id and if valid fetch customer object
  Assignment assignment=assignmentService.getSingleAssignment(aId);
  		  //after checking it is valid delete it 
  		assignmentService.DeleteAssignmentById(assignment);
  		messageDto.setMessage("assignment record hard deleted from DB!!");
  		messageDto.setStatus(200);
  		return ResponseEntity.ok(messageDto);
  	}
  	
   
    
    
}
