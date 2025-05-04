package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/modules")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CourseModuleController {

   

    @Autowired
    private CourseModuleRepository courseModuleRepo;

    @Autowired
    private CourseRepository courseRepo;

    // Logger instance
    private final org.slf4j.Logger logger = LoggerFactory.getLogger("CourseModuleController");

    // Folder path to save uploaded files (should ideally come from application.properties)
    private static final String UPLOAD_DIR = "C:/Users/ragip/OneDrive/Documents/JAVA FULL STACK HEX/Job-Portal/uploads";

    /**
     * POST /addmodule/{courseId}
     * Add a module with multiple files (e.g., images, videos, documents).
     */
    @PostMapping("/addmodule/{courseId}")
    public ResponseEntity<?> addModuleWithFiles(
            @PathVariable int courseId,
            @RequestParam String title,
            @RequestParam MultipartFile[] files
    ) throws IOException {

        // Check if the course exists
        Course course = courseRepo.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

        // Ensure upload directory exists
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Store file URLs for this module
        StringBuilder urls = new StringBuilder();

        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            urls.append("/uploads/").append(fileName).append(",");
        }

        // Save new CourseModule
        CourseModule module = new CourseModule();
        module.setCourse(course);
        module.setTitle(title);
        module.setUrl(urls.toString().replaceAll(",$", "")); // remove trailing comma

        courseModuleRepo.save(module);
        logger.info("Module added for course ID {} with files: {}", courseId, module.getUrl());

        return ResponseEntity.ok("Module created");
    }

    /**
     * GET /modules/{id}
     * Get a module by its ID.
     */
    @GetMapping("/modules/{id}")
    public ResponseEntity<CourseModule> getModuleById(@PathVariable int id) {
        CourseModule module = courseModuleRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Module not found"));
        return ResponseEntity.ok(module);
    }

    /**
     * PUT /update/{id}
     * Update an existing module title and/or uploaded files.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateModule(
            @PathVariable int id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MultipartFile[] files
    ) throws IOException {

        CourseModule module = courseModuleRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Module not found"));

        // Update the title if provided
        if (title != null && !title.trim().isEmpty()) {
            module.setTitle(title);
        }

        // Replace old files if new ones are provided
        if (files != null && files.length > 0) {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            StringBuilder urls = new StringBuilder();

            for (MultipartFile file : files) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);

                urls.append("/uploads/").append(fileName).append(",");
            }

            module.setUrl(urls.toString().replaceAll(",$", ""));
        }

        courseModuleRepo.save(module);
        logger.info("Module with ID {} updated", id);

        return ResponseEntity.ok(module);
    }

    /**
     * GET /course/{courseId}
     * Get all modules that belong to a specific course.
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseModule>> getModulesByCourse(@PathVariable int courseId) {
        Course course = courseRepo.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

        List<CourseModule> modules = courseModuleRepo.findByCourse(course);
        return ResponseEntity.ok(modules);
    }

    /**
     * DELETE /delete/{id}
     * Delete a module by ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteModule(@PathVariable int id) {
        CourseModule module = courseModuleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        courseModuleRepo.delete(module);
        logger.info("Deleted module with ID: {}", id);
        return ResponseEntity.ok("Module deleted successfully");
    }
}
