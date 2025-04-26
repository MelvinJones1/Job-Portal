
package com.spring.rest_api.career_crafter.controller;



import com.spring.rest_api.career_crafter.model.CourseModule;


import com.spring.rest_api.career_crafter.service.CourseModuleService;



import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleService moduleService;

    org.slf4j.Logger logger = LoggerFactory.getLogger("CourseModuleController");

    // Add a module to a course
    @PostMapping("/addmodule/course/{courseId}")
    public CourseModule addModule(@PathVariable int courseId, @RequestBody CourseModule module) {
        logger.info("Adding module to the course with ID: {}", courseId);
        CourseModule savedModule = moduleService.addModuleToCourse(courseId, module);
        logger.info("Module added successfully: {}", savedModule);
        return savedModule;
    }

    // Update a course module by its ID
    @PutMapping("/update/{moduleId}")
    public CourseModule updateModule(@PathVariable int moduleId, @RequestBody CourseModule module) {
        logger.info("Updating course module with ID: {}", moduleId);
        CourseModule updated = moduleService.updateCourseModule(moduleId, module);
        logger.info("Updated module successfully: {}", updated);
        return updated;
    }

    // Delete a course module by its ID
    @DeleteMapping("/delete/{moduleId}")
    public void deleteModule(@PathVariable int moduleId) {
        logger.info("Deleting module with ID: {}", moduleId);
        moduleService.deleteCourseModule(moduleId);
        logger.info("Deleted module successfully.");
    }

    // Get paginated modules for a specific course
    @GetMapping("/getmodulesPaginated/{courseId}")
    public List<CourseModule> getModulesByCourseId(@PathVariable int courseId,
                                                   @RequestParam int page,
                                                   @RequestParam int size) {
        logger.info("Fetching paginated modules for course ID: {}", courseId);
        Pageable pageable = PageRequest.of(page, size);
        return moduleService.getModulesByCourseIdPaginated(courseId, pageable);
    }
}