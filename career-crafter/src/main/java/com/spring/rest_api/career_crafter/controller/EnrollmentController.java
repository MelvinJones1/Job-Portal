		
		package com.spring.rest_api.career_crafter.controller;
		
		import com.spring.rest_api.career_crafter.model.Enrollment;
		
		import com.spring.rest_api.career_crafter.service.EnrollmentService;
		
		
		
		
		
		import org.slf4j.LoggerFactory;
		import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
		import org.springframework.data.domain.Pageable;
		
		import org.springframework.web.bind.annotation.*;
		
		import java.util.List;
		@RestController
		@RequestMapping("/api/enrollments")
		//@CrossOrigin(origins = {"http://localhost:5173"})
		public class EnrollmentController {
		
		    @Autowired
		    private EnrollmentService enrollmentService;
		
		    org.slf4j.Logger logger = LoggerFactory.getLogger("EnrollmentController");
		
		    // Get all enrollments with pagination
		    @GetMapping("/getAll")
		    public Page<Enrollment> getAllEnrollments(
		            @RequestParam int page,
		            @RequestParam int size) {
		        
		        Pageable pageable = PageRequest.of(page - 1, size);  // Page is 1-based, adjust for 0-based
		        return enrollmentService.getAllEnrollmentsPaginated(pageable);
		    }
		
		    // Get enrollments by category
		    @GetMapping("/getByCategory/{categoryName}")
		    public List<Enrollment> getEnrollmentsByCategory(@PathVariable String categoryName) {
		        logger.info("Fetching enrollments by category: {}", categoryName);
		        return enrollmentService.getEnrollmentsByCategory(categoryName);
		    }
		
		    // Get enrollments by job seeker name
		    @GetMapping("/getByJobseeker/{name}")
		    public List<Enrollment> getEnrollmentsByJobSeeker(@PathVariable String name) {
		        logger.info("Fetching enrollments for job seeker name: {}", name);
		        return enrollmentService.getEnrollmentsByJobSeekerName(name);
		    }
		
		    // Get total number of enrollments
		    @GetMapping("/count")
		    public long getTotalEnrollments() {
		        logger.info("Fetching total number of enrollments.");
		        return enrollmentService.getTotalEnrollments();
		    }
		
		    // Get count of completed enrollments
		    @GetMapping("/countCompleted")
		    public long getCompletedEnrollments() {
		        logger.info("Fetching count of completed enrollments.");
		        return enrollmentService.getCompletedEnrollments();
		    }
		}