
package com.spring.rest_api.career_crafter.controller;



import java.util.List;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.service.CourseService;



@RestController
@RequestMapping("/api/course")
public class CourseController {


	@Autowired
	private CourseService courseService;
	
	
	 org.slf4j.Logger logger =  LoggerFactory.getLogger("CourseController"); 


	     @PostMapping("/add")
	     public Course addCourse(@RequestBody Course course) {
	         return courseService.addCourse(course);
	     }

	     @GetMapping("/getAllCourses")
	     public List<Course> getAllCourses(@RequestParam int page, @RequestParam int size) {
	         Pageable pageable = PageRequest.of(page, size);
	         return courseService.getAllCourses(pageable);
	     }

	     @GetMapping("/get/{cId}")
	     public Course getSingleCourse(@PathVariable int cId) {
	         return courseService.getSingleCourse(cId);
	     }

	     @PutMapping("/update/{cId}")
	     public Course updateTheCourse(@PathVariable int cId, @RequestBody Course updateCourse) {
	         return courseService.updateTheCourse(cId, updateCourse);
	     }

	     @DeleteMapping("/delete/{cId}")
	     public String deleteCourseById(@PathVariable int cId) {
	         courseService.deleteCourseById(cId);
	         return "Course record deleted successfully!";
	     }

	     @GetMapping("/category/{category}")
	     public List<Course> getCoursesByCategory(@PathVariable String category) {
	         return courseService.getCoursesByCategory(category);
	     }

	     @GetMapping("/courses/search")
	     public List<Course> searchCourses(@RequestParam String title) {
	         return courseService.searchCoursesByTitle(title);
	     }

	     @GetMapping("/count")
	     public long getCourseCount() {
	         return courseService.getCourseCount();
	     }
	 }


