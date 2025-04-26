
package com.spring.rest_api.career_crafter.service;

import java.io.IOException;




import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Instructor;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.AuthRepository;
import com.spring.rest_api.career_crafter.repository.InstructorRepository;
@Service
public class InstructorService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    // Get the instructor profile while logged in
    public Instructor getInstructorProfile(String username) {
        User user = authRepository.findByUsername(username);
        return instructorRepository.findByUser(user);
    }

    // Save the instructor profile
    public Instructor createInstructorProfile(Instructor instructor, String username) {
        User user = authRepository.findByUsername(username);
        instructor.setUser(user);
        return instructorRepository.save(instructor);
    }

    // Get single instructor
    public Instructor getSingleInstructor(int id) throws InvalidIDException {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Instructor ID " + id + " is invalid."));
    }

    // Update the instructor
    public Instructor updateInstructorProfile(int insId, Instructor updatedInstructor) throws InvalidIDException {
        Instructor existingInstructor = instructorRepository.findById(insId)
                .orElseThrow(() -> new InvalidIDException("Instructor with ID " + insId + " not found."));
        
        // Update only allowed fields
        existingInstructor.setFirstName(updatedInstructor.getFirstName());
        existingInstructor.setLastName(updatedInstructor.getLastName());
        existingInstructor.setEmail(updatedInstructor.getEmail());
        existingInstructor.setMobileNumber(updatedInstructor.getMobileNumber());
        existingInstructor.setProfileImagePath(updatedInstructor.getProfileImagePath());
        existingInstructor.setCertifications(updatedInstructor.getCertifications());
        
        return instructorRepository.save(existingInstructor);
    }

    // Delete the instructor profile
    public void deleteInstructorById(Instructor instructor) {
        instructorRepository.delete(instructor);
    }

    // Upload the image of the instructor
    public Instructor uploadImage(MultipartFile file, int insId) throws IOException, InvalidIDException {
        Instructor instructor = instructorRepository.findById(insId)
                .orElseThrow(() -> new InvalidIDException("Instructor ID " + insId + " is invalid."));

        // Validate image type
        List<String> allowedExtensions = Arrays.asList("png", "jpg", "jpeg", "gif", "svg");
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
        if (!allowedExtensions.contains(extension)) {
            throw new RuntimeException("Invalid image type.");
        }

        // Define upload path
        String uploadPath = "C:\\Users\\ragip\\OneDrive\\Documents\\JAVA FULL STACK HEX\\hexawareproject\\career-crafter\\uploads";
        Files.createDirectories(Paths.get(uploadPath));
        
        // Save the image to the upload path
        Path path = Paths.get(uploadPath, originalFileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        // Save the image path in the database
        instructor.setProfileImagePath(path.toString());
        return instructorRepository.save(instructor);
    }
}