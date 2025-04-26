
package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;


import com.spring.rest_api.career_crafter.model.Assignment;

import com.spring.rest_api.career_crafter.repository.AssignmentRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    // Add Assignment
    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    // Get All Assignments with Pagination
    public Page<Assignment> getAllAssignments(Pageable pageable) {
        return assignmentRepository.findAll(pageable);
    }

    // Get Single Assignment by ID
    public Assignment getSingleAssignment(int aId) throws InvalidIDException {
        return assignmentRepository.findById(aId)
                .orElseThrow(() -> new InvalidIDException("Assignment with ID " + aId + " not found."));
    }

    // Update Assignment
    public Assignment updateTheAssignment(int aId, Assignment updateAssignment) throws InvalidIDException {
        Assignment existingAssignment = assignmentRepository.findById(aId)
                .orElseThrow(() -> new InvalidIDException("Assignment with ID " + aId + " not found."));

        // Update only allowed fields
        existingAssignment.setTitle(updateAssignment.getTitle());
        existingAssignment.setDescription(updateAssignment.getDescription());
        existingAssignment.setUrl(updateAssignment.getUrl());
        existingAssignment.setSubmissionDeadline(updateAssignment.getSubmissionDeadline());

        return assignmentRepository.save(existingAssignment); // Save updated assignment
    }

    // Delete Assignment by ID
    public void deleteAssignmentById(int aId) throws InvalidIDException {
        Assignment existingAssignment = assignmentRepository.findById(aId)
                .orElseThrow(() -> new InvalidIDException("Assignment with ID " + aId + " not found."));
        assignmentRepository.delete(existingAssignment); // Perform deletion
    }
}