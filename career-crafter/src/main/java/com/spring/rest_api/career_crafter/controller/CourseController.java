package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {


	@Autowired
	private CourseService courseService;

	
	@GetMapping("/get/{cId}")
	public Course getSingleCourse(@PathVariable int cId) {
		return courseService.getSingleCourse(cId);
		
	}

}
