
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Assignment;




import com.spring.rest_api.career_crafter.service.AssignmentService;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    Logger logger = LoggerFactory.getLogger(AssignmentController.class);

    // Add Assignment
    @PostMapping("/add")
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        logger.info("Attempting to add new assignment.");
        Assignment saved = assignmentService.addAssignment(assignment);
        logger.info("Assignment added successfully: {}", saved);
        return saved; // Return created assignment directly
    }

    // Get All Assignments with Pagination
    @GetMapping
    public List<Assignment> getAllAssignments(@RequestParam int page, @RequestParam int size) {
        logger.info("Fetching all assignments with pagination.");
        Pageable pageable = PageRequest.of(page, size);
        List<Assignment> assignments = assignmentService.getAllAssignments(pageable).getContent();
        logger.info("Found {} assignments.", assignments.size());
        return assignments; // Return the list of assignments directly
    }

    // Get Single Assignment by ID
    @GetMapping("/get/{aId}")
    public Assignment getSingleAssignment(@PathVariable int aId) throws InvalidIDException {
        logger.info("Fetching assignment with ID: {}", aId);
        return assignmentService.getSingleAssignment(aId); // Throw exception if ID is invalid
    }

    // Update Assignment
    @PutMapping("/update/{aId}")
    public Assignment updateTheAssignment(@PathVariable int aId, @RequestBody Assignment updateAssignment) throws InvalidIDException {
        logger.info("Updating assignment with ID: {}", aId);
        Assignment updated = assignmentService.updateTheAssignment(aId, updateAssignment);
        logger.info("Assignment updated successfully: {}", updated);
        return updated; // Return updated assignment directly
    }

    // Delete Assignment by ID
    @DeleteMapping("/delete/{aId}")
    public void deleteAssignmentById(@PathVariable int aId) throws InvalidIDException {
        logger.info("Attempting to delete assignment with ID: {}", aId);
        assignmentService.deleteAssignmentById(aId); // Throw exception if ID is invalid
        logger.info("Assignment with ID: {} deleted successfully.", aId);
    }
}