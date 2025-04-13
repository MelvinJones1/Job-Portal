
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseReview;
import com.spring.rest_api.career_crafter.service.CourseReviewService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getall")
    public ResponseEntity<?> getAllReviews() {
        try {
            List<CourseReview> reviews = courseReviewService.getAllReviews();
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            messageDto.setMessage("Failed to fetch reviews.");
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }
   
    @GetMapping("/course/{courseId}")
    public List<CourseReview> getReviewsByCourseId(@PathVariable int courseId) {
        List<CourseReview> reviews =courseReviewService.getReviewsByCourseId(courseId);
        return reviews;
    }
}
