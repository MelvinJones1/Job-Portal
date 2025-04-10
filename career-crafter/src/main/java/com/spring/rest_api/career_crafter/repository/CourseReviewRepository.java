package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Integer> {
}
