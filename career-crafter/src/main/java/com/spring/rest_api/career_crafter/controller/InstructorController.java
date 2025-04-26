
package com.spring.rest_api.career_crafter.controller;

import java.io.IOException;



import java.security.Principal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.spring.rest_api.career_crafter.service.InstructorService;

import com.spring.rest_api.career_crafter.exception.*;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    org.slf4j.Logger logger = LoggerFactory.getLogger("InstructorController");

    // Get the details of the instructor profile
    @GetMapping("/getProfile")
    public Instructor getInstructorProfile(Principal principal) {
        logger.info("Fetching the instructor profile.");
        String username = principal.getName();
        return instructorService.getInstructorProfile(username);
    }

    // Create the profile of the instructor
    @PostMapping("/createprofile")
    public Instructor createInstructorProfile(@RequestBody Instructor instructor, Principal principal) {
        logger.info("Creating instructor profile.");
        if (instructor.getFirstName() == null || instructor.getFirstName().isEmpty()) {
            throw new RuntimeException("Instructor name cannot be empty.");
        }
        String username = principal.getName();
        return instructorService.createInstructorProfile(instructor, username);
    }

    // Update the instructor profile
    @PutMapping("/update/{insId}")
    public Instructor updateInstructorProfile(@PathVariable int insId, @RequestBody Instructor updatedInstructor) throws InvalidIDException {
        logger.info("Updating the instructor profile with ID: {}", insId);
        return instructorService.updateInstructorProfile(insId, updatedInstructor);
    }

    // Delete the instructor profile by ID
    @DeleteMapping("/delete/profile/{insId}")
    public void deleteInstructorById(@PathVariable int insId) throws InvalidIDException {
        logger.info("Deleting the instructor profile with ID: {}", insId);
        Instructor instructor = instructorService.getSingleInstructor(insId);
        instructorService.deleteInstructorById(instructor);
        logger.info("Instructor profile deleted successfully.");
    }

    // Upload the image of the instructor
    @PostMapping("/image/upload/{Insid}")
    public Instructor uploadImage(@PathVariable int Insid, @RequestParam MultipartFile file) throws IOException, InvalidIDException {
        logger.info("Uploading image for instructor with ID: {}", Insid);
        return instructorService.uploadImage(file, Insid);
    }
}