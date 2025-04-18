
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseContent;

import com.spring.rest_api.career_crafter.service.CourseContentService;


import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;

    

    org.slf4j.Logger logger =  LoggerFactory.getLogger("CourseContentController"); 
    //add the coursecontent to the module
    @PostMapping("/add/module/{moduleId}")
    public CourseContent addContent(@PathVariable int moduleId, @RequestBody CourseContent content) {
       logger.info("adding the coursecontent");
    	CourseContent saved = contentService.addContentToModule(moduleId, content);
    	logger.info("addedd the course contents successfully");
        return saved;
    }

    @PutMapping("/update/{contentId}")
    //updated the course contenrt with coursecontent id
    public CourseContent updateContent(@PathVariable int contentId, @RequestBody CourseContent content) {
        logger.info("updating the course content");
    	CourseContent updated = contentService.updateContent(contentId, content);
    	logger.info("updated the course content successfully");
        return updated;
    }

    //delets the course conrent with the content id
    @DeleteMapping("/{contentId}")
    public void deleteContent(@PathVariable int contentId) {
        contentService.deleteContent(contentId);
        logger.info("deleted the course content");
        
    }
    @GetMapping("/module/{moduleId}/paginated")
    public ResponseEntity<?> getContentsByModuleId(@PathVariable int moduleId,
                                                            @RequestParam int page,
                                                            @RequestParam int size) {
        logger.info("Fetching contents for module ID {} with pagination", moduleId);
        Pageable pageable = PageRequest.of(page, size);
        try {
            List<CourseContent> contents = contentService.getContentsByModuleId(moduleId, pageable);
            return ResponseEntity.ok(contents);
        } catch (Exception e) {
            logger.error("Error fetching contents: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to fetch contents: " + e.getMessage());
        }
    }
    
   
    @PostMapping("/add/multiple/module/{moduleId}")
    public ResponseEntity<?> addMultipleContentsToModule(@PathVariable int moduleId, @RequestBody List<CourseContent> contents) {
        logger.info("Adding multiple contents to module ID: {}", moduleId);
        try {
            List<CourseContent> savedContents = contentService.addMultipleContentsToModule(moduleId, contents);
            return ResponseEntity.ok(savedContents);
        } catch (Exception e) {
            logger.error("Error adding contents: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to add contents: " + e.getMessage());
        }
    }
}
