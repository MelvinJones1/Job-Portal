
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

    public List<CourseReview> getAllReviewsPaginated(Pageable pageable) {
        return courseReviewRepository.findAll(pageable).getContent();
    }
    public List<CourseReview> getReviewsByCourseId(int courseId) {
        return courseReviewRepository.findByCourseId(courseId);
    }
}
