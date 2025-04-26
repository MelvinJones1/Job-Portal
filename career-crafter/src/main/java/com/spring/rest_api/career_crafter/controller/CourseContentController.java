
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseContent;


import com.spring.rest_api.career_crafter.service.CourseContentService;


import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/contents")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;

    org.slf4j.Logger logger = LoggerFactory.getLogger("CourseContentController");

    // Add course content to a module
    @PostMapping("/add/module/{moduleId}")
    public CourseContent addContent(@PathVariable int moduleId, @RequestBody CourseContent content) {
        logger.info("Adding course content to module ID: {}", moduleId);
        CourseContent saved = contentService.addContentToModule(moduleId, content);
        logger.info("Added course content successfully.");
        return saved;
    }

    // Update course content by content ID
    @PutMapping("/update/{contentId}")
    public CourseContent updateContent(@PathVariable int contentId, @RequestBody CourseContent content) {
        logger.info("Updating course content with ID: {}", contentId);
        CourseContent updated = contentService.updateContent(contentId, content);
        logger.info("Updated course content successfully.");
        return updated;
    }

    // Delete course content by content ID
    @DeleteMapping("/{contentId}")
    public void deleteContent(@PathVariable int contentId) {
        logger.info("Deleting course content with ID: {}", contentId);
        contentService.deleteContent(contentId);
        logger.info("Deleted course content successfully.");
    }

    // Get paginated course content by module ID
    @GetMapping("/module/{moduleId}/paginated")
    public List<CourseContent> getContentsByModuleId(@PathVariable int moduleId,
                                                     @RequestParam int page,
                                                     @RequestParam int size) {
        logger.info("Fetching paginated course contents for module ID: {}", moduleId);
        Pageable pageable = PageRequest.of(page, size);
        return contentService.getContentsByModuleId(moduleId, pageable);
    }

    // Add multiple course contents to a module
    @PostMapping("/add/multiple/module/{moduleId}")
    public List<CourseContent> addMultipleContentsToModule(@PathVariable int moduleId, @RequestBody List<CourseContent> contents) {
        logger.info("Adding multiple course contents to module ID: {}", moduleId);
        List<CourseContent> savedContents = contentService.addMultipleContentsToModule(moduleId, contents);
        logger.info("Added multiple course contents successfully.");
        return savedContents;
    }
}