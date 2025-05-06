
package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.CourseReview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Integer> {
    List<CourseReview> findByCourseId(int courseId); // Find reviews by course IDvoid deleteByCourseId(int courseId);
   
	void deleteByCourseId(int courseId);

}