
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

    @PostMapping("/image/upload/{Insid}")
    public Instructor uploadImage(@PathVariable int Insid, @RequestParam MultipartFile file) throws IOException, InvalidIDException {
        logger.info("Uploading image for instructor with ID: {}", Insid);
        return instructorService.uploadImage(file, Insid);
    }
}