package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	/**
	 * Retrieves all enrollments with pagination support.
	 *
	 * @param pageable Pagination details
	 * @return A list of enrollments within the requested page
	 */
	public List<Enrollment> getAllEnrollmentsPaginated(Pageable pageable) {
		return enrollmentRepository.findAll(pageable).getContent(); // Extract content from Page
	}

	/**
	 * Retrieves enrollments filtered by course category.
	 *
	 * @param categoryName The category name to filter enrollments by
	 * @return A list of enrollments in the specified category
	 */
	public List<Enrollment> getEnrollmentsByCategory(String categoryName) {
		return enrollmentRepository.findByCourseCategory(categoryName); // Fetch enrollments by course category
	}

	/**
	 * Retrieves enrollments by job seeker name.
	 *
	 * @param name The name of the job seeker
	 * @return A list of enrollments for the given job seeker name
	 */
	public List<Enrollment> getEnrollmentsByJobSeekerName(String name) {
		return enrollmentRepository.findByJobSeekerName(name); // Fetch enrollments by job seeker name
	}

	/**
	 * Retrieves the total number of enrollments.
	 *
	 * @return The total count of enrollments
	 */
	public long getTotalEnrollments() {
		return enrollmentRepository.count(); // Count total enrollments
	}

	/**
	 * Retrieves the count of completed enrollments.
	 *
	 * @return The count of enrollments marked as completed
	 */
	public long getCompletedEnrollments() {
		return enrollmentRepository.countByCompleted(true); // Count completed enrollments
	}
}
