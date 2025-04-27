package com.spring.rest_api.career_crafter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.repository.CourseCategoryRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    
    // Add a new course
    public Course addCourse(Course course, int categoryId) {
        // Fetch the category by ID
        CourseCategory category = courseCategoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category ID " + categoryId + " not found"));
        
        // Set the category for the course
        course.setCategory(category);

        // Save the course
        return courseRepository.save(course);
    }

    // Get all courses with pagination
    public List<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).getContent();
    }

    // Get a single course by its ID
    public Course getSingleCourse(int id) throws InvalidIDException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Course ID " + id + " is invalid."));
    }

    // Update a course by its ID
    public Course updateTheCourse(int id, Course updatedCourse) throws InvalidIDException {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Course ID " + id + " not found."));
        
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setAboutTheCourse(updatedCourse.getAboutTheCourse());
        existingCourse.setDifficultyLevel(updatedCourse.getDifficultyLevel());
        existingCourse.setCreatedAt(updatedCourse.getCreatedAt());
        existingCourse.setEnrolled(updatedCourse.isEnrolled());
        
        CourseCategory category = courseCategoryRepository.findById(updatedCourse.getCategory().getId())
                .orElseThrow(() -> new InvalidIDException("Category ID " + updatedCourse.getCategory().getId() + " is invalid."));
        existingCourse.setCategory(category);

        return courseRepository.save(existingCourse);
    }

    // Delete a course by its ID
    public void deleteCourseById(int id) throws InvalidIDException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Course ID " + id + " is invalid."));
        courseRepository.delete(course);
    }

    // Get courses by category name
    public List<Course> getCoursesByCategoryTitle(String categoryTitle) {
        return courseRepository.findByCategoryTitle(categoryTitle);
    }

    // Search for courses by title
    public List<Course> searchCoursesByTitle(String title) {
        return courseRepository.findByTitleContaining(title);
    }

    // Get the total count of courses
    public long getCourseCount() {
        return courseRepository.count();
    }
}