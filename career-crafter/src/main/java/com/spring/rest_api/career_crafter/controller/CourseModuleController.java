package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.service.CourseModuleService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleService moduleService;

    @Autowired
    private MessageResponseDto messageDto;

    @PostMapping("/create")
    public ResponseEntity<?> createModule(@RequestBody CourseModule module, @RequestParam int courseId) {
        try {
            CourseModule savedModule = moduleService.addModule(module, courseId);
            return ResponseEntity.ok(savedModule);
        } catch (Exception e) {
            messageDto.setMessage("Failed to add module: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }
}
