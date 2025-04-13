
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseContent;

import com.spring.rest_api.career_crafter.service.CourseContentService;


import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
//displays all the contents ny module id
    @GetMapping("/module/{moduleId}")
    public List<CourseContent> getContentsByModuleId(@PathVariable int moduleId) {
        List<CourseContent> contents = contentService.getContentsByModuleId(moduleId);
        logger.info("returned all the contents by module id");
        return contents;
    }
}
