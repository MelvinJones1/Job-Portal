
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.service.EnrollmentService;
import com.spring.rest_api.career_crafter.dto.MessageResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private MessageResponseDto messageDto;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEnrollments() {
        try {
            List<Enrollment> list = enrollmentService.getAllEnrollments();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            messageDto.setMessage("Unable to fetch enrollments.");
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }
    
    @GetMapping("/getByCategory/{categoryName}")
    public List<Enrollment> getEnrollmentsByCategory(@PathVariable String categoryName) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCategory(categoryName);
        return enrollments;
    }

    @GetMapping("/getByJobseeker/{name}")
    public List<Enrollment> getEnrollmentsByJobSeeker(@PathVariable String name) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByJobSeekerName(name);
        return enrollments;
    }
    
}
