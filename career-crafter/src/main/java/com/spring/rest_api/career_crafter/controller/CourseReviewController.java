package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseReview;

import com.spring.rest_api.career_crafter.service.CourseReviewService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
//@CrossOrigin(origins = {"http://localhost:5173"})
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    private static final Logger logger = LoggerFactory.getLogger(CourseReviewController.class);

    /**
     * GET /api/reviews/getAll?page={page}&size={size}
     * Retrieve all course reviews with pagination.
     *
     * @param page page number (0-based index)
     * @param size number of records per page
     * @return paginated list of CourseReview
     */
    @GetMapping("/getAll")
    public Page<CourseReview> getAllReviews(@RequestParam int page, @RequestParam int size) {
        logger.info("Fetching all reviews with pagination (page: {}, size: {}).", page, size);
        PageRequest pageable = PageRequest.of(page, size);
        return courseReviewService.getAllReviewsPaginated(pageable);
    }

    /**
     * GET /api/reviews/course/{courseId}
     * Retrieve all reviews for a specific course.
     *
     * @param courseId the ID of the course
     * @return list of CourseReview for the given course
     */
    @GetMapping("/course/{courseId}")
    public List<CourseReview> getReviewsByCourseId(@PathVariable int courseId) {
        logger.info("Fetching reviews for course ID: {}", courseId);
        List<CourseReview> reviews = courseReviewService.getReviewsByCourseId(courseId);

        if (reviews.isEmpty()) {
            logger.warn("No reviews found for course ID: {}", courseId);
            throw new RuntimeException("No reviews found for the specified course.");
        }

        logger.info("Successfully fetched {} review(s) for course ID: {}", reviews.size(), courseId);
        return reviews;
    }
}
