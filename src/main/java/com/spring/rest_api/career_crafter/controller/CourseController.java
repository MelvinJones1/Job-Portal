package com.spring.rest_api.career_crafter.controller;



import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.dto.MessageResponseDto;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.service.CourseService;



@RestController
@RequestMapping("/api/course")
public class CourseController {


	@Autowired
	private CourseService courseService;
	@Autowired
	private MessageResponseDto dto;
	
	
	@PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {      //add the course
        return courseService.addCourse(course);
    }
	 @GetMapping("/getAllCourses")
	    public List<Course> getAllCourses() {                     //get all courses
	        return courseService.getAllCourses();
	    }
	 
	@GetMapping("/get/{cId}")
	public Course getSingleCourse(@PathVariable int cId) {
		return courseService.getSingleCourse(cId);
		
	}
	

	//update the course put mapping
	@PutMapping("/update/{cId}")
	public ResponseEntity<?> updateTheCourse(@PathVariable int cId,
            @RequestBody Course updateCourse) {
try {
Course course = courseService.updateTheCourse(cId, updateCourse);
return ResponseEntity.ok(course);
} catch (InvalidIDException e) {

dto.setMessage(e.getMessage());
dto.setStatus(400);
return ResponseEntity.status(400).body(dto);
} 
}
	//delete the course

	@DeleteMapping("/delete/{cId}")
	public ResponseEntity<?> DeletecourseById(@PathVariable int cId) throws InvalidIDException {
		//lets validate id and if valid fetch customer object
		Course course = courseService.getSingleCourse(cId);
		  //after checking it is valid delete it 
		courseService.DeletecourseById(course);
		dto.setMessage("Course record hard deleted from DB!!");
		dto.setStatus(200);
		return ResponseEntity.ok(dto);
	}
	
	//get courses by category
	@GetMapping("/category/{category}")
	public List<Course> getCoursesByCategory(@PathVariable String category) {
	    List<Course> courses = courseService.getCoursesByCategory(category);
	    return courses;
	}
	
	//get by name/title of course
	@GetMapping("/api/courses/search")
	public List<Course> searchCourses(@RequestParam String title) {
	    List<Course> courses = courseService.searchCoursesByTitle(title);
	   return courses;
	}

	
	
}
