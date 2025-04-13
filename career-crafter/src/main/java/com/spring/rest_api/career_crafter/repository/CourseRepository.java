
package com.spring.rest_api.career_crafter.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	List<Course> findByCategory(String category);
	List<Course> findByTitle(String title);

}
