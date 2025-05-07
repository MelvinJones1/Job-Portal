package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.service.CourseService;

@RestController
@RequestMapping("/api/course")
//@CrossOrigin(origins = {"http://localhost:5173"}) // Allow requests from the frontend
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Logger instance for tracking controller activity
    Logger logger = LoggerFactory.getLogger("CourseController");

    /**
     * POST /add
     * Adds a new course
     * Steps:
     * 1. Accept a Course object from the frontend.
     * 2. Call the service to persist the course.
     * 3. Log and return the saved course.
     */
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        logger.info("Adding a new course.");
        Course savedCourse = courseService.addCourse(course); // Save the course
        logger.info("Added the course successfully", savedCourse);
        return savedCourse;
    }

    /**
     * GET /getAll
     * Fetch all courses with pagination
     * Steps:
     * 1. Accept `page` and `size` parameters from the request.
     * 2. Use PageRequest to create a Pageable object.
     * 3. Fetch paginated courses from the service.
     * 4. Return the paginated result.
     */
    @GetMapping("/getAll")
    public Page<Course> getAllCourses(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size); // Create pageable object
        return courseService.getAllCourses(pageable);    // Return paginated list
    }

    /**
     * GET /get/{courseId}
     * Fetch a specific course by its ID
     * Steps:
     * 1. Receive courseId as a path variable.
     * 2. Use the service to fetch course.
     * 3. Throw exception if not found.
     * 4. Return course.
     */
    @GetMapping("/get/{courseId}")
    public Course getCourseById(@PathVariable int courseId) throws InvalidIDException {
        logger.info("Fetching course with ID", courseId);
        return courseService.getCourseById(courseId); // Throws exception if not found
    }

    /**
     * PUT /update/{courseId}
     * Update an existing course
     * Steps:
     * 1. Receive courseId and the new course details.
     * 2. Delegate to the service to update.
     * 3. Log and return the updated course.
     */
    @PutMapping("/update/{courseId}")
    public Course updateCourse(@PathVariable int courseId, @RequestBody Course updateCourse) throws InvalidIDException {
        logger.info("Updating course with ID", courseId);
        Course updatedCourse = courseService.updateCourse(courseId, updateCourse);
        logger.info("Updated course successfully", updatedCourse);
        return updatedCourse;
    }

    /**
     * DELETE /delete/{courseId}
     * Delete a course by ID
     * Steps:
     * 1. Receive courseId from the path.
     * 2. Call service to perform deletion.
     * 3. Log the deletion.
     */
    @DeleteMapping("/delete/{courseId}")
    public void deleteCourse(@PathVariable int courseId) throws InvalidIDException {
        logger.info("Deleting course with ID", courseId);
        courseService.deleteCourse(courseId); // Delegate deletion to service
        logger.info("Deleted course with ID", courseId);
    }

    /**
     * GET /count
     * Return the total number of courses
     */
    @GetMapping("/count")
    public long getCourseCount() {
        return courseService.getCourseCount();
    }

    /**
 
     * 1. Accept a `title` query parameter.
     * 2. Use service to find all matching course titles.
     * 3. Return result list.
     */
	    @GetMapping("/courses/search")
	    public List<Course> searchCourses(@RequestParam String title) {
	    	logger.info("searching course by"+title);
	        return courseService.searchCoursesByTitle(title); // Partial match search
	    }
}
