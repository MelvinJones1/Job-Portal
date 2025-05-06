
package com.spring.rest_api.career_crafter.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.spring.rest_api.career_crafter.service.CourseService;
import com.spring.rest_api.career_crafter.service.EnrollmentService;
import com.spring.rest_api.career_crafter.service.InstructorService;

import com.spring.rest_api.career_crafter.exception.*;

@RestController
@RequestMapping("/api/instructor")
//@CrossOrigin(origins = {"http://localhost:5173"})
public class InstructorController {
	 @Autowired
	    private CourseService courseService;

	    @Autowired
	    private EnrollmentService enrollmentService;

    @Autowired
    private InstructorService instructorService;

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
   
    @GetMapping("/dashboard/metrics")
    public Map<String, Object> getDashboardMetrics() {
        long totalCourses = courseService.getCourseCount();
        long totalEnrolledUsers = enrollmentService.getTotalEnrollments();

        Map<String, Object> response = new HashMap<>();
        response.put("totalCourses", totalCourses);
        response.put("totalEnrolledUsers", totalEnrolledUsers);

        return response;
    }
    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) throws MalformedURLException {
        // Define the upload directory
        Path imagePath = Paths.get("C:/Users/ragip/OneDrive/Documents/JAVA FULL STACK HEX/Job-Portal/uploads", filename);

        // Check if the image exists
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        // Create a Resource object to return the file
        Resource resource = new UrlResource(imagePath.toUri());

        // Set the content type (optional, you can set it based on the file)
        String contentType = "application/octet-stream"; // Default content type
        try {
            contentType = Files.probeContentType(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    

    @PostMapping("/image/upload/{Insid}")
    public Instructor uploadImage(@PathVariable int Insid, @RequestParam MultipartFile file) throws IOException, InvalidIDException {
        logger.info("Uploading image for instructor with ID: {}", Insid);

        // Call the service to handle the upload
        return instructorService.uploadImage(file, Insid);
    }

    
}