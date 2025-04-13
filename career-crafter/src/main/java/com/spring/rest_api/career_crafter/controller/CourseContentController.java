package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseContent;
import com.spring.rest_api.career_crafter.service.CourseContentService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;

    @Autowired
    private MessageResponseDto messageDto;

    @PostMapping("/addcontent")
    public ResponseEntity<?> addContent(@RequestBody CourseContent content,
                                        @RequestParam int moduleId,
                                        @RequestParam(required = false) Integer assignmentId) {
        try {
            CourseContent saved = contentService.addContent(content, moduleId, assignmentId);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            messageDto.setMessage("Failed to add content: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }
}
