
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseReview;

import com.spring.rest_api.career_crafter.service.CourseReviewService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    org.slf4j.Logger logger = LoggerFactory.getLogger("CourseReviewController");

    // Get all reviews with pagination
    @GetMapping("/getAll")
    public List<CourseReview> getAllReviews(@RequestParam int page, @RequestParam int size) {
        logger.info("Fetching all reviews with pagination.");
        Pageable pageable = PageRequest.of(page, size);
        return courseReviewService.getAllReviewsPaginated(pageable);
    }

    // Get reviews by course ID
    @GetMapping("/course/{courseId}")
    public List<CourseReview> getReviewsByCourseId(@PathVariable int courseId) {
        logger.info("Fetching reviews for course ID: {}", courseId);
        List<CourseReview> reviews = courseReviewService.getReviewsByCourseId(courseId);
        if (reviews.isEmpty()) {
            logger.warn("No reviews found for course ID: {}", courseId);
            throw new RuntimeException("No reviews found for the specified course."); // Exception will be handled globally
        }
        logger.info("Successfully fetched {} reviews for course ID: {}", reviews.size(), courseId);
        return reviews;
    }
}