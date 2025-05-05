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
@CrossOrigin(origins = {"http://localhost:5173"})
public class CourseModuleController {

    @Autowired
    private CourseModuleService courseModuleService;

    private final org.slf4j.Logger logger = LoggerFactory.getLogger("CourseModuleController");

    @PostMapping("/addmodule/{courseId}")
    public String addModuleWithFiles(
            @PathVariable int courseId,
            @RequestParam String title,
            @RequestParam MultipartFile[] files
    ) throws IOException {
        logger.info("Adding module to courseId: {}", courseId);
        return courseModuleService.addModule(courseId, title, files);
    }

    @GetMapping("/modules/{id}")
    public CourseModule getModuleById(@PathVariable int id) {
        return courseModuleService.getModule(id);
    }

    @PutMapping("/update/{id}")
    public CourseModule updateModule(
            @PathVariable int id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MultipartFile[] files
    ) throws IOException {
        logger.info("Updating module with ID: {}", id);
        return courseModuleService.updateModule(id, title, files);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseModule> getModulesByCourse(@PathVariable int courseId) {
        return courseModuleService.getModulesByCourse(courseId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteModule(@PathVariable int id) {
        logger.info("Deleting module with ID: {}", id);
        return courseModuleService.deleteModule(id);
    }
}
