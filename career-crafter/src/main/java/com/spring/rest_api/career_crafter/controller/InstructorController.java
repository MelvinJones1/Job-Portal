
package com.spring.rest_api.career_crafter.controller;

import java.io.IOException;



import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.rest_api.career_crafter.model.Instructor;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.service.AuthService;
import com.spring.rest_api.career_crafter.service.CourseService;
import com.spring.rest_api.career_crafter.service.EnrollmentService;
import com.spring.rest_api.career_crafter.service.InstructorService;

import com.spring.rest_api.career_crafter.exception.*;

@RestController
@RequestMapping("/api/instructor")
@CrossOrigin(origins = {"http://localhost:5173"})
public class InstructorController {

    @Autowired
    private InstructorService instructorService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AuthService authSerivce;

    org.slf4j.Logger logger = LoggerFactory.getLogger("InstructorController");

    @GetMapping("/getProfile")
    public Instructor getInstructorProfile(Principal principal) {
        logger.info("Fetching the instructor profile for: {}", principal.getName());
        String username = principal.getName();
        return instructorService.getInstructorProfile(username);
    }

    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody Instructor instructor) throws InvalidUsernameException {
        logger.info("Adding a new instructor: {}", instructor.getFirstName());

        // Create the User first
        User user = new User();
        user.setUsername(instructor.getUser().getUsername());
        user.setPassword(instructor.getUser().getPassword()); // Use raw password, it will be encoded in the service
        user.setRole("INSTRUCTOR");  // Set the role of the user to INSTRUCTOR
        user = authSerivce.customerSignUp(user); // This will save the user and encode the password
        if (instructor.getHighestQualification() == null) {
            instructor.setHighestQualification("Not Provided");  // Provide a default value
        }

        // Link the created user to the instructor
        instructor.setUser(user);

        // Save and return the instructor
        return instructorService.addInstructor(instructor);
    }

    @PutMapping("/update/{insId}")
    public Instructor updateInstructorProfile(@PathVariable int insId, @RequestBody Instructor updatedInstructor) throws InvalidIDException {
        logger.info("Updating the instructor profile with ID: {}", insId);
        return instructorService.updateInstructorProfile(insId, updatedInstructor);
    }

    @DeleteMapping("/delete/profile/{insId}")
    public void deleteInstructorById(@PathVariable int insId) throws InvalidIDException {
        logger.info("Deleting the instructor profile with ID: {}", insId);
        Instructor instructor = instructorService.getSingleInstructor(insId);
        instructorService.deleteInstructorById(instructor);
        logger.info("Instructor profile deleted successfully.");
    }

    @PostMapping("/image/upload/{Insid}")
    public Instructor uploadImage(@PathVariable int Insid, @RequestParam MultipartFile file) throws IOException, InvalidIDException {
        logger.info("Uploading image for instructor with ID: {}", Insid);
        return instructorService.uploadImage(file, Insid);
    }
    
    @GetMapping("/dashboard/metrics")
    public Map<String, Long> getDashboardMetrics() {
        logger.info("Fetching dashboard metrics: total courses and enrollments.");

        // Fetch the metrics
        long totalEnrollments = enrollmentService.getTotalEnrollments();
        long totalCourses = courseService.getCourseCount();

        // Prepare the response
        Map<String, Long> metrics = new HashMap<>();
        metrics.put("totalEnrollments", totalEnrollments);
        metrics.put("totalCourses", totalCourses);

        return metrics;
    }

}