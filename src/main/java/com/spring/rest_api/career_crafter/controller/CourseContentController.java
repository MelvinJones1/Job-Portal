package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseContent;

import com.spring.rest_api.career_crafter.service.CourseContentService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;

    


    @PostMapping("/add/module/{moduleId}")
    public CourseContent addContent(@PathVariable int moduleId, @RequestBody CourseContent content) {
        CourseContent saved = contentService.addContentToModule(moduleId, content);
        return saved;
    }

    @PutMapping("/update/{contentId}")
    public CourseContent updateContent(@PathVariable int contentId, @RequestBody CourseContent content) {
        CourseContent updated = contentService.updateContent(contentId, content);
        return updated;
    }

    @DeleteMapping("/{contentId}")
    public void deleteContent(@PathVariable int contentId) {
        contentService.deleteContent(contentId);
        
    }

    @GetMapping("/module/{moduleId}")
    public List<CourseContent> getContentsByModuleId(@PathVariable int moduleId) {
        List<CourseContent> contents = contentService.getContentsByModuleId(moduleId);
        return contents;
    }
}

