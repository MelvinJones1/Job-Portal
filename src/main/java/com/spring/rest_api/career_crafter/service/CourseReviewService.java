package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.CourseReview;
import com.spring.rest_api.career_crafter.repository.CourseReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseReviewService {

    @Autowired
    private CourseReviewRepository courseReviewRepository;

    public List<CourseReview> getAllReviews() {
        return courseReviewRepository.findAll();
    }
    
    public List<CourseReview> getReviewsByCourseId(int courseId) {
        return courseReviewRepository.findByCourseId(courseId);
    }
}
