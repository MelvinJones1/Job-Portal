package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.repository.AssignmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    // Add
    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    // Update
    public Assignment updateAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    // Delete
    public void deleteAssignment(int id) throws InvalidIDException {
        if (!assignmentRepository.existsById(id)) {
            throw new InvalidIDException("Assignment not found with ID: " + id);
        }
        assignmentRepository.deleteById(id);
    }

    // Get by ID
    public Optional<Assignment> getAssignmentById(int id) {
        return assignmentRepository.findById(id);
    }

    // Pagination
    public Page<Assignment> getAllAssignmentsPaginated(int page, int size) {
        return assignmentRepository.findAll(PageRequest.of(page, size));
    }

    // Count
    public long getTotalAssignments() {
        return assignmentRepository.count();
    }
}
