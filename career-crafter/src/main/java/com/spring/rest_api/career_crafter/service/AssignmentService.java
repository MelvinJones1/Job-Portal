package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.repository.AssignmentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {

	private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);

	@Autowired
	private AssignmentRepository assignmentRepository;

	/**
	 * Adds a new assignment to the database.
	 */
	public Assignment addAssignment(Assignment assignment) {
		logger.info("Adding new assignment: {}", assignment.getTitle());
		return assignmentRepository.save(assignment);
	}

	/**
	 * Retrieves all assignments with pagination.
	 */
	public Page<Assignment> getAllAssignments(Pageable pageable) {
		logger.info("Fetching all assignments with pagination. Page: {}, Size: {}", pageable.getPageNumber(),
				pageable.getPageSize());
		return assignmentRepository.findAll(pageable);
	}

	/**
	 * Retrieves a single assignment by ID.
	 */
	public Assignment getSingleAssignment(int aId) throws InvalidIDException {
		logger.info("Fetching assignment with ID: {}", aId);
		return assignmentRepository.findById(aId).orElseThrow(() -> {
			logger.warn("Assignment not found with ID: {}", aId);
			return new InvalidIDException("Assignment with ID " + aId + " not found.");
		});
	}

	/**
	 * Updates an existing assignment.
	 */
	public Assignment updateTheAssignment(int aId, Assignment updateAssignment) throws InvalidIDException {
		logger.info("Updating assignment with ID: {}", aId);
		Assignment existingAssignment = assignmentRepository.findById(aId).orElseThrow(() -> {
			logger.warn("Cannot update. Assignment not found with ID: {}", aId);
			return new InvalidIDException("Assignment with ID " + aId + " not found.");
		});

		existingAssignment.setTitle(updateAssignment.getTitle());
		existingAssignment.setDescription(updateAssignment.getDescription());
		existingAssignment.setUrl(updateAssignment.getUrl());
		existingAssignment.setSubmissionDeadline(updateAssignment.getSubmissionDeadline());

		logger.info("Assignment updated successfully with ID: {}", aId);
		return assignmentRepository.save(existingAssignment);
	}

	/**
	 * Deletes an assignment by ID.
	 */
	public void deleteAssignmentById(int aId) throws InvalidIDException {
		logger.info("Deleting assignment with ID: {}", aId);
		Assignment existingAssignment = assignmentRepository.findById(aId).orElseThrow(() -> {
			logger.warn("Cannot delete. Assignment not found with ID: {}", aId);
			return new InvalidIDException("Assignment with ID " + aId + " not found.");
		});

		assignmentRepository.delete(existingAssignment);
		logger.info("Assignment deleted successfully with ID: {}", aId);
	}

	/**
	 * Gets the total number of assignments in the system.
	 */
	public long getTotalAssignmentCount() {
		long count = assignmentRepository.count();
		logger.info("Total assignments count: {}", count);
		return count;
	}
}
