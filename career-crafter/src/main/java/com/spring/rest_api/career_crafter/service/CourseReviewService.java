package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.CourseReview;
import com.spring.rest_api.career_crafter.repository.CourseReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseReviewService {

	@Autowired
	private CourseReviewRepository courseReviewRepository;

	/**
	 * Retrieves paginated course reviews.
	 * 
	 * @param pageable Pageable object containing pagination information
	 * @return A list of course reviews based on pagination parameters
	 */
	public List<CourseReview> getAllReviewsPaginated(Pageable pageable) {
		// Ensure that the pageable object is not null and has a valid page size
		if (pageable == null || pageable.getPageSize() <= 0) {
			throw new IllegalArgumentException("Invalid pagination parameters.");
		}

		// Fetch and return the content of reviews as per the pagination details
		return courseReviewRepository.findAll(pageable).getContent();
	}

	/**
	 * Retrieves reviews for a specific course based on course ID.
	 * 
	 * @param courseId The ID of the course for which reviews are fetched
	 * @return A list of course reviews related to the specified course ID
	 */
	public List<CourseReview> getReviewsByCourseId(int courseId) {
		// Ensure that the courseId is valid (non-zero or positive)
		if (courseId <= 0) {
			throw new IllegalArgumentException("Invalid course ID.");
		}

		// Fetch and return the reviews for the given course ID
		return courseReviewRepository.findByCourseId(courseId);
	}
}
