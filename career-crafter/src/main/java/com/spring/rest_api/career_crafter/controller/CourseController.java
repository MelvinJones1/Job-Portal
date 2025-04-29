package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.repository.CourseCategoryRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;
import com.spring.rest_api.career_crafter.service.CourseService;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private CourseRepository courseRepository;

    org.slf4j.Logger logger = LoggerFactory.getLogger("CourseController");
    // Add a new course
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course, @RequestParam int categoryId) {
        logger.info("Adding a new course with category ID: {}", categoryId);
        Course savedCourse = courseService.addCourse(course, categoryId);
        logger.info("Course added successfully: {}", savedCourse);
        return savedCourse;
    }



    // Get all courses with pagination
    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseService.getAllCourses(pageable);
    }

    // Get a single course by its ID
    @GetMapping("/get/{cId}")
    public Course getSingleCourse(@PathVariable int cId) throws InvalidIDException {
        return courseService.getSingleCourse(cId);
    }

    // Update a course by its ID
    @PutMapping("/update/{cId}")
    public Course updateTheCourse(@PathVariable int cId, @RequestBody Course updateCourse) throws InvalidIDException {
        return courseService.updateTheCourse(cId, updateCourse);
    }

    // Delete a course by its ID
    @DeleteMapping("/delete/{cId}")
    public String deleteCourseById(@PathVariable int cId) throws InvalidIDException {
        courseService.deleteCourseById(cId);
        return "Course record deleted successfully!";
    }

    // Get courses by category name
    @GetMapping("/category/{categoryTitle}")
    public List<Course> getCoursesByCategory(@PathVariable String categoryTitle) {
        return courseService.getCoursesByCategoryTitle(categoryTitle);
    }

    // Search for courses by title
    @GetMapping("/courses/search")
    public List<Course> searchCourses(@RequestParam String title) {
        return courseService.searchCoursesByTitle(title);
    }

    // Get the total number of courses
    @GetMapping("/count")
    public long getCourseCount() {
        return courseService.getCourseCount();
    }
}