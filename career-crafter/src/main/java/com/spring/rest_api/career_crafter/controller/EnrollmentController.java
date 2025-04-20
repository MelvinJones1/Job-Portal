
package com.spring.rest_api.career_crafter.controller;

import com.spring.rest_api.career_crafter.model.Enrollment;

import com.spring.rest_api.career_crafter.service.EnrollmentService;



import com.spring.rest_api.career_crafter.dto.MessageResponseDto;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;
    org.slf4j.Logger logger =  LoggerFactory.getLogger("EnrollmentController"); 
    @Autowired
    private MessageResponseDto messageDto;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEnrollments(@RequestParam int page,
                                                        @RequestParam int size) {
        logger.info("Fetching all enrollments with pagination");
        Pageable pageable = PageRequest.of(page, size);
        try {
            List<Enrollment> enrollments = enrollmentService.getAllEnrollmentsPaginated(pageable);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            logger.error("Error fetching paginated enrollments: {}", e.getMessage());
            messageDto.setMessage("Error fetching paginated enrollments: " + e.getMessage());
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
    @GetMapping("/count")
    public ResponseEntity<?> getTotalEnrollments() {
        logger.info("Fetching total number of enrollments");
        try {
            long count = enrollmentService.getTotalEnrollments();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            logger.error("Error fetching enrollment count: {}", e.getMessage());
            messageDto.setMessage("Error fetching enrollment count: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }

    @GetMapping("/countCompleted")
    public ResponseEntity<?> getCompletedEnrollments() {
        logger.info("Fetching count of completed enrollments");
        try {
            long count = enrollmentService.getCompletedEnrollments();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            logger.error("Error fetching completed enrollment count: {}", e.getMessage());
            messageDto.setMessage("Error fetching completed enrollment count: " + e.getMessage());
            messageDto.setStatus(500);
            return ResponseEntity.status(500).body(messageDto);
        }
    }
    
}
