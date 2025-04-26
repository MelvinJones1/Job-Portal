
package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class CourseModuleService {

    @Autowired
    private CourseModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Add a module to a course
    public CourseModule addModuleToCourse(int courseId, CourseModule module) {
        if (module.getTitle() == null || module.getTitle().isEmpty()) {
            throw new RuntimeException("Module title cannot be empty.");
        }
        if (module.getUrl() == null || module.getUrl().isEmpty()) {
            throw new RuntimeException("Module URL cannot be empty.");
        }
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with ID " + courseId + " not found."));
        module.setCourse(course);
        return moduleRepository.save(module);
    }

    // Update a course module by ID
    public CourseModule updateCourseModule(int moduleId, CourseModule updatedModule) {
        CourseModule existingModule = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module with ID " + moduleId + " not found."));

        // Update allowed fields
        existingModule.setTitle(updatedModule.getTitle());
        existingModule.setUrl(updatedModule.getUrl());

        return moduleRepository.save(existingModule);
    }

    // Delete a course module by ID
    public void deleteCourseModule(int moduleId) {
        if (!moduleRepository.existsById(moduleId)) {
            throw new RuntimeException("Module with ID " + moduleId + " not found.");
        }
        moduleRepository.deleteById(moduleId);
    }

    // Get paginated modules for a course by course ID
    public List<CourseModule> getModulesByCourseIdPaginated(int courseId, Pageable pageable) {
        return moduleRepository.findByCourseId(courseId, pageable).getContent();
    }
}