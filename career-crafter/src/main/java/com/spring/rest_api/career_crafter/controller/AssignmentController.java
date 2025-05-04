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
@CrossOrigin(origins = { "http://localhost:5173" }) // Allow frontend requests from this origin
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService; // Inject the service layer

    Logger logger = LoggerFactory.getLogger(AssignmentController.class); // Logger for tracking operations

    /**
     * POST /add - Create a new assignment
     * Steps:
     * 1. Receive assignment object from frontend via request body.
     * 2. Pass it to the service layer for saving.
     * 3. Log and return the saved assignment.
     */
    @PostMapping("/add")
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        logger.info("Attempting to add new assignment.");
        Assignment saved = assignmentService.addAssignment(assignment); // Save assignment
        logger.info("Assignment added successfully: {}", saved);
        return saved; // Return the saved assignment as response
    }

    /**
     * GET /
     * Steps:
     * 1. Read pagination parameters (page number and size).
     * 2. Create a Pageable object.
     * 3. Call the service to fetch paginated result.
     * 4. Extract the content (list of assignments) from the result and return.
     */
    @GetMapping
    public List<Assignment> getAllAssignments(@RequestParam int page, @RequestParam int size) {
        logger.info("Fetching all assignments with pagination.");
        Pageable pageable = PageRequest.of(page, size); // Define pagination settings
        List<Assignment> assignments = assignmentService.getAllAssignments(pageable).getContent(); // Fetch paginated data
        if (assignments.isEmpty()) {
            logger.warn("No assignments found for the given pagination parameters.");
        } else {
            logger.info("Found {} assignments.", assignments.size());
        }
        return assignments;
    }

    /**
     * GET /get/{aId} - Fetch a single assignment by ID
     * Steps:
     * 1. Capture the assignment ID from the URL.
     * 2. Call the service to retrieve the assignment.
     * 3. If not found, throw InvalidIDException.
     * 4. Return the assignment if found.
     */
    @GetMapping("/get/{aId}")
    public Assignment getSingleAssignment(@PathVariable int aId) throws InvalidIDException {
    	 logger.debug("Fetching assignment with ID: {}", aId);
        logger.info("Fetching assignment with ID: {}", aId);
        return assignmentService.getSingleAssignment(aId); // Fetch single assignment by ID
    }

    /**
     * PUT /update/{aId} - Update an existing assignment
     * Steps:
     * 1. Capture assignment ID and updated data from request.
     * 2. Pass both to the service for update.
     * 3. If ID is invalid, throw exception.
     * 4. Return updated assignment.
     */
    @PutMapping("/update/{aId}")
    public Assignment updateTheAssignment(@PathVariable int aId, @RequestBody Assignment updateAssignment)
            throws InvalidIDException {
        logger.info("Updating assignment with ID: {}", aId);
        Assignment updated = assignmentService.updateTheAssignment(aId, updateAssignment); // Update logic in service
        logger.info("Assignment updated successfully: {}", updated);
        return updated;
    }

    /**
     * DELETE /delete/{aId} - Delete an assignment by ID
     * Steps:
     * 1. Capture assignment ID from URL.
     * 2. Call service to delete the assignment.
     * 3. If ID doesn't exist, throw exception.
     * 4. Log successful deletion.
     */
    @DeleteMapping("/delete/{aId}")
    public void deleteAssignmentById(@PathVariable int aId) throws InvalidIDException {
        logger.info("Attempting to delete assignment with ID: {}", aId);
        assignmentService.deleteAssignmentById(aId); // Service handles validation and deletion
        logger.info("Assignment with ID: {} deleted successfully.", aId);
    }

    /**
     * GET /count - Get total number of assignments
     * Steps:
     * 1. Call the service to count total assignments.
     * 2. Return the count.
     */
    @GetMapping("/count")
    public long getTotalAssignmentCount() {
    	logger.debug("Fetching total assignment count.");
        logger.info("Fetching total assignment count.");
        return assignmentService.getTotalAssignmentCount(); // Count all records in assignment table
    }
}
