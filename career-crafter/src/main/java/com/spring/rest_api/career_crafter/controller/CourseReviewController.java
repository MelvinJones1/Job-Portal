
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseReview;
import com.spring.rest_api.career_crafter.service.CourseReviewService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    @Autowired
    private MessageResponseDto messageDto;
    org.slf4j.Logger logger =  LoggerFactory.getLogger("CourseReviewController"); 
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllReviews(@RequestParam int page,
                                                    @RequestParam int size) {
        logger.info("Fetching all reviews with pagination");
        Pageable pageable = PageRequest.of(page, size);
        try {
            List<CourseReview> reviews = courseReviewService.getAllReviewsPaginated(pageable);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            logger.error("Error fetching paginated reviews: {}", e.getMessage());
            messageDto.setMessage("Failed to fetch reviews.");
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }
    
   
    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getReviewsByCourseId(@PathVariable int courseId) {
        logger.info("Fetching reviews for course ID: {}", courseId);
        try {
            List<CourseReview> reviews = courseReviewService.getReviewsByCourseId(courseId);
            if (reviews.isEmpty()) {
                logger.warn("No reviews found for course ID: {}", courseId);
                return ResponseEntity.status(404).body("No reviews found for the specified course.");
            }
            logger.info("Successfully fetched {} reviews for course ID: {}", reviews.size(), courseId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            logger.error("Error fetching reviews for course ID {}: {}", courseId, e.getMessage());
            return ResponseEntity.status(500).body("An error occurred while fetching reviews.");
        }
    }
}
