package com.spring.rest_api.career_crafter.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.dto.MessageResponseDto;
import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.service.CourseService;


@RestController
@RequestMapping("/api/course")
public class CourseController {


	@Autowired
	private CourseService courseService;
	@Autowired
	private MessageResponseDto messageDto;
	@PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {      //add the course
        return courseService.addCourse(course);
    }
	 @GetMapping("/getall")
	    public List<Course> getAllCourses() {                     //get all courses
	        return courseService.getAllCourses();
	    }
	 
	
	@GetMapping("/get/{cId}")
	public Course getSingleCourse(@PathVariable int cId) {
		return courseService.getSingleCourse(cId);
		
	}

}
