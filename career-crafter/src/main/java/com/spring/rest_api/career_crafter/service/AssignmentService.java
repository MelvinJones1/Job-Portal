
package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;

import com.spring.rest_api.career_crafter.model.Assignment;

import com.spring.rest_api.career_crafter.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

	public Assignment getSingleAssignment(int aId) {
		Optional<Assignment> optional = assignmentRepository.findById(aId);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Assignment Id");
		return optional.get();
	}
	
	//update the Assignment
		public Assignment updateTheAssignment(int aId, Assignment updateAssignment) throws InvalidIDException {
	  	    // Check if the Assignment exists
	  	    Assignment Oldassign = assignmentRepository.findById(aId)
	  	            .orElseThrow(() -> new InvalidIDException("Assignent with ID " + aId + " not found"));

	  	    // Update fields  only those allowed to change
	  	    Oldassign.setDescription(updateAssignment.getDescription());
	  	    Oldassign.setTitle(updateAssignment.getTitle());
	  	    Oldassign.setUrl(updateAssignment.getUrl());
	  	    Oldassign.setSubmissionDeadline(updateAssignment.getSubmissionDeadline());
	  	   return assignmentRepository.save(Oldassign);
	  	}
	//delete the course 
		public void DeleteAssignmentById(Assignment assignment) {

	                     assignmentRepository.delete(assignment);
			
		}
}
