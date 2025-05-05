package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.service.AssignmentService;
import com.spring.rest_api.career_crafter.service.CourseModuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/assignment")
@CrossOrigin(origins = { "http://localhost:5173" })
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private CourseModuleService courseModuleService;

    Logger logger = LoggerFactory.getLogger(AssignmentController.class);

    // Add Assignment and link with CourseModule
    @PostMapping("/add/{courseId}")
    public Assignment createAssignment(@PathVariable int courseId, @RequestBody Assignment assignment) throws InvalidIDException {
        logger.info("Adding new assignment to course module ID: {}", courseId);

        CourseModule courseModule = courseModuleService.getCourseModuleById(courseId);
        if (courseModule == null) {
            logger.error("Invalid Course Module ID: {}", courseId);
            throw new InvalidIDException("Invalid Course Module ID");
        }

        assignment.setCourseModule(courseModule);
        return assignmentService.addAssignment(assignment);
    }

    // Update Assignment
    @PutMapping("/update/{id}")
    public Assignment updateAssignment(@PathVariable int id, @RequestBody Assignment updatedAssignment) throws InvalidIDException {
        Assignment existing = assignmentService.getAssignmentById(id)
            .orElseThrow(() -> new InvalidIDException("Assignment not found with ID: " + id));

        existing.setTitle(updatedAssignment.getTitle());
        existing.setDescription(updatedAssignment.getDescription());
        existing.setUrl(updatedAssignment.getUrl());
        existing.setSubmissionDeadline(updatedAssignment.getSubmissionDeadline());
        existing.setCertificate(updatedAssignment.getCertificate());

        return assignmentService.updateAssignment(existing);
    }

    // Delete Assignment
    @DeleteMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable int id) throws InvalidIDException {
        assignmentService.deleteAssignment(id);
        return "Assignment deleted successfully with ID: " + id;
    }

    // Get Assignment by ID
    @GetMapping("/{id}")
    public Assignment getAssignmentById(@PathVariable int id) throws InvalidIDException {
        return assignmentService.getAssignmentById(id)
            .orElseThrow(() -> new InvalidIDException("Assignment not found with ID: " + id));
    }

    // Get Paginated Assignments
    @GetMapping
    public Page<Assignment> getAssignments(@RequestParam int page,
                                           @RequestParam int size) {
        logger.info("Fetching paginated assignments: page={}, size={}", page, size);
        return assignmentService.getAllAssignmentsPaginated(page, size);
    }

    // Get Total Count of Assignments
    @GetMapping("/count")
    public long getTotalAssignments() {
        long count = assignmentService.getTotalAssignments();
        logger.info("Total assignments count: {}", count);
        return count;
    }
}
