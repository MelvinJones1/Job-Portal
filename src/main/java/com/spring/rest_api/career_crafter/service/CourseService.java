package com.spring.rest_api.career_crafter.service;



import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.repository.CourseRepository;


@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	


	
	public Course addCourse(Course course) {
        return courseRepository.save(course); // JPA built-in method
    }
	
	public List<Course> getAllCourses() {
        return courseRepository.findAll(); // Built-in method
    }
	
	public Course getSingleCourse(int cId) {
		Optional<Course> optional = courseRepository.findById(cId);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Course Id");
		return optional.get();
	}
	
	//get by course id
public Course getById(int catId) throws InvalidIDException {
		
		Optional<Course> optional = courseRepository.findById(catId);
		if(optional.isEmpty())
			throw new InvalidIDException("Course ID Invalid..");
		
		return optional.get();
	}

//update the course
	public Course updateTheCourse(int cId, Course updateCourse) throws InvalidIDException {
  	    // Check if the course exists
  	    Course oldcourse = courseRepository.findById(cId)
  	            .orElseThrow(() -> new InvalidIDException("Course with ID " + cId + " not found"));

  	    // Update fields  only those allowed to change
  	    oldcourse.setTitle(updateCourse.getTitle());
  	    oldcourse.setAboutTheCourse(updateCourse.getAboutTheCourse());
  	    oldcourse.setCategory(updateCourse.getCategory());
  	    oldcourse.setDescription(updateCourse.getDescription());
  	    oldcourse.setDifficultyLevel(updateCourse.getDifficultyLevel());
  	    oldcourse.setCreatedAt(updateCourse.getCreatedAt());
  	   return courseRepository.save(oldcourse);
  	}
//delete the course 
	public void DeletecourseById(Course course) {

                     courseRepository.delete(course);
		
	}
	//filter  courses by category
	public List<Course> getCoursesByCategory(String category) {
	    return courseRepository.findByCategory(category);
	}
	//find by title
	public List<Course> searchCoursesByTitle(String title) {
	    return courseRepository.findByTitle(title);

	
	
	}
	
	

}
