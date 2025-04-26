
package com.spring.rest_api.career_crafter.service;



import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(Course course) {
        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            throw new RuntimeException("Course title cannot be empty");
        }
        if (course.getCategory() == null || course.getCategory().isEmpty()) {
            throw new RuntimeException("Course category cannot be empty");
        }
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).getContent();
    }

    public Course getSingleCourse(int cId) {
        return courseRepository.findById(cId)
                .orElseThrow(() -> new RuntimeException("Invalid Course Id"));
    }

    public Course updateTheCourse(int cId, Course updateCourse) {
        if (updateCourse.getTitle() == null || updateCourse.getTitle().isEmpty()) {
            throw new RuntimeException("Course title cannot be empty");
        }

        Course existingCourse = courseRepository.findById(cId)
                .orElseThrow(() -> new RuntimeException("Course with ID " + cId + " not found"));

        existingCourse.setTitle(updateCourse.getTitle());
        existingCourse.setAboutTheCourse(updateCourse.getAboutTheCourse());
        existingCourse.setCategory(updateCourse.getCategory());
        existingCourse.setDescription(updateCourse.getDescription());
        existingCourse.setDifficultyLevel(updateCourse.getDifficultyLevel());
        existingCourse.setCreatedAt(updateCourse.getCreatedAt());

        return courseRepository.save(existingCourse);
    }

    public void deleteCourseById(int cId) {
        Course course = courseRepository.findById(cId)
                .orElseThrow(() -> new RuntimeException("Invalid Course Id"));
        courseRepository.delete(course);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> searchCoursesByTitle(String title) {
        return courseRepository.findByTitle(title);
    }

    public long getCourseCount() {
        return courseRepository.count();
    }
}