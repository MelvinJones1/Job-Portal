package com.spring.rest_api.career_crafter.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.repository.AuthRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;


@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	

	@Autowired
	private AuthRepository authRepository;
	
	
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
	
	

	
	

}
