
package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.model.CourseModule;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Integer> {
	

	List<CourseModule> findByCourseId(int courseId);
	void deleteByCourseId(int courseId);

	List<CourseModule> findByCourse(Course course);
}
	