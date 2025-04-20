
package com.spring.rest_api.career_crafter.controller;



import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	 org.slf4j.Logger logger =  LoggerFactory.getLogger("CourseController"); 
	@PostMapping("/add")
    public Course addCourse(@RequestBody Course course) { 
		 logger.info("course addedd success");//add the course
        return courseService.addCourse(course);
       
    }@GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
logger.info("Fetching all courses with pagination");
Pageable pageable = PageRequest.of(page, size);
try {
List<Course> courses = courseService.getAllCourses(pageable);
return ResponseEntity.ok(courses);
} catch (Exception e) {
logger.error("Error fetching paginated courses: {}", e.getMessage());
dto.setMessage("Error fetching paginated courses: " + e.getMessage());
dto.setStatus(500);
return ResponseEntity.status(500).body(dto);
}
}
	 
	@GetMapping("/get/{cId}")
	public Course getSingleCourse(@PathVariable int cId) {
		logger.info("getting course by id");
		return courseService.getSingleCourse(cId);
		
	}
	

	//update the course put mapping
	@PutMapping("/update/{cId}")
	public ResponseEntity<?> updateTheCourse(@PathVariable int cId,
            @RequestBody Course updateCourse) {
		logger.info("updating course by id");
try {
Course course = courseService.updateTheCourse(cId, updateCourse);
return ResponseEntity.ok(course);
} catch (InvalidIDException e) {
logger.error("error updating the course");
dto.setMessage(e.getMessage());
dto.setStatus(400);
return ResponseEntity.status(400).body(dto);
} 
}
	//delete the course

	@DeleteMapping("/delete/{cId}")
	public ResponseEntity<?> DeletecourseById(@PathVariable int cId) throws InvalidIDException {
		//lets validate id and if valid fetch customer object
		logger.info("deleting course by id");
		Course course = courseService.getSingleCourse(cId);
		  //after checking it is valid delete it 
		courseService.DeletecourseById(course);
		logger.info("deleeted course ");
		dto.setMessage("Course record hard deleted from DB!!");
		dto.setStatus(200);
		return ResponseEntity.ok(dto);
	}
	
	//get courses by category
	@GetMapping("/category/{category}")
	public List<Course> getCoursesByCategory(@PathVariable String category) {
		logger.info("getting courses by category");
	    List<Course> courses = courseService.getCoursesByCategory(category);
	    return courses;
	}
	
	//get by name/title of course
	@GetMapping("/courses/search")
	public List<Course> searchCourses(@RequestParam String title) {
	    List<Course> courses = courseService.searchCoursesByTitle(title);
	   return courses;
	}

	@GetMapping("/count")
	public ResponseEntity<?> getCourseCount() {
	    logger.info("Fetching total number of courses");
	    try {
	        long count = courseService.getCourseCount();
	        return ResponseEntity.ok(count);
	    } catch (Exception e) {
	        logger.error("Error fetching course count: {}", e.getMessage());
	        dto.setMessage("Error fetching course count: " + e.getMessage());
	        dto.setStatus(500);
	        return ResponseEntity.status(500).body(dto);
	    }
	}
	
}
