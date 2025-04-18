
package com.spring.rest_api.career_crafter.service;



import com.spring.rest_api.career_crafter.model.CourseContent;
import com.spring.rest_api.career_crafter.model.CourseModule;

import com.spring.rest_api.career_crafter.repository.CourseContentRepository;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseContentService {

    @Autowired
    private CourseContentRepository contentRepository;

    @Autowired
    private CourseModuleRepository moduleRepository;



    public CourseContent addContentToModule(int moduleId, CourseContent content) {
        if (content.getContentTitle() == null || content.getContentTitle().isEmpty()) {
            throw new RuntimeException("Content title cannot be empty");
        }
        CourseModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        content.setCourseModule(module);
        return contentRepository.save(content);
    }
    public CourseContent updateContent(int contentId, CourseContent updatedContent) {
        CourseContent existing = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        existing.setContentTitle(updatedContent.getContentTitle());
        existing.setContentUrl(updatedContent.getContentUrl());
        existing.setAssignment(updatedContent.getAssignment());  //  updating assignment,url and title

        return contentRepository.save(existing);
    }

    public void deleteContent(int contentId) {
        if (!contentRepository.existsById(contentId)) {
            throw new RuntimeException("Content not found");
        }
        contentRepository.deleteById(contentId);
    }

    public List<CourseContent> getContentsByModuleId(int moduleId, Pageable pageable) {
        return contentRepository.findByCourseModuleId(moduleId, pageable).getContent();
    }
    
   
    public List<CourseContent> addMultipleContentsToModule(int moduleId, List<CourseContent> contents) {
        CourseModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        contents.forEach(content -> content.setCourseModule(module));
        return contentRepository.saveAll(contents);
    }
}//
