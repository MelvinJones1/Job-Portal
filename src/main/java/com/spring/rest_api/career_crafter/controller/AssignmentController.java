package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.service.AssignmentService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private MessageResponseDto messageDto;

    @PostMapping("/postassignment")
    public ResponseEntity<?> createAssignment(@RequestBody Assignment assignment) {
        try {
            Assignment saved = assignmentService.addAssignment(assignment);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            messageDto.setMessage("Failed to add assignment: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }
    
}
