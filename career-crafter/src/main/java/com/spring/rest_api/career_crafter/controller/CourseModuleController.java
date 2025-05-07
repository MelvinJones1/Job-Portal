package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.service.CourseModuleService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/modules")
//@CrossOrigin(origins = {"http://localhost:5173"})
public class CourseModuleController {

    @Autowired
    private CourseModuleService courseModuleService;

    private final org.slf4j.Logger logger = LoggerFactory.getLogger("CourseModuleController");

    /**
     * Adds a new course module with associated media files to a specific course.
     *
     * @param courseId the ID of the course to which the module will be added
     * @param title the title of the course module
     * @param files array of media files (images, videos, documents) to associate with the module
     * a status message indicating success or failure
     *  
     */
    @PostMapping("/addmodule/{courseId}")
    public String addModuleWithFiles(
            @PathVariable int courseId,
            @RequestParam String title,
            @RequestParam MultipartFile[] files
    ) throws IOException {
        logger.info("Adding module to courseId", courseId);
        return courseModuleService.addModule(courseId, title, files);
    }

    /**
     * Retrieves a specific course module by its ID.
     *
     * id the ID of the module to retrieve
     *  the CourseModule object
     */
    @GetMapping("/modules/{id}")
    public CourseModule getModuleById(@PathVariable int id) {
        return courseModuleService.getModule(id);
    }

    /**
     * Updates an existing course module's title and media files.
     *
     * @param id the ID of the module to update
     * @param title the new title for the module
     * @param files new media files to associate with the module
     *  the updated CourseModule object
     *  IOException if file handling fails
     */
    @PutMapping("/update/{id}")
    public CourseModule updateModule(
            @PathVariable int id,
            @RequestParam String title,
            @RequestParam MultipartFile[] files
    ) throws IOException {
        logger.info("Updating module with ID", id);
        return courseModuleService.updateModule(id, title, files);
    }

    /**
     * Retrieves all modules associated with a specific course.
     *
     * @param courseId the ID of the course
     *  a list of CourseModule objects
     */
    @GetMapping("/course/{courseId}")
    public List<CourseModule> getModulesByCourse(@PathVariable int courseId) {
        return courseModuleService.getModulesByCourse(courseId);
    }

    /**
     * Deletes a course module by its ID.
     *
     * @param id the ID of the module to delete
     *  a status message indicating success or failure
     */
    @DeleteMapping("/delete/{id}")
    public String deleteModule(@PathVariable int id) {
        logger.info("Deleting module with ID", id);
        return courseModuleService.deleteModule(id);
    }
}
