
package com.spring.rest_api.career_crafter.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	
	long count();
	List<Course> findByTitle(String title);
	List<Course> findByCategoryTitle(String categoryTitle);
	List<Course> findByTitleContaining(String title);

}
