
package com.spring.rest_api.career_crafter.controller;

import java.io.IOException;


import java.security.Principal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.spring.rest_api.career_crafter.dto.MessageResponseDto;
import com.spring.rest_api.career_crafter.exception.*;


@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	org.slf4j.Logger logger =  LoggerFactory.getLogger("InstructorController"); 
	@Autowired
	private MessageResponseDto dto;
	//get the details of the instructor profile
	@GetMapping("/getProfile")
	public Instructor getInstructorProfile(Principal principal)
	
	{ logger.info("gets the instructor profile");
		String username=principal.getName();
		return instructorService.getInstructorProfile(username);
	
	}
	
	//create the profile of the instructor
	@PostMapping("/createprofile")
	 public Instructor createInstructorProfile(@RequestBody Instructor instructor, Principal principal) {
	        String username = principal.getName();
	        return instructorService.createInstructorProfile(instructor, username);
	    }
	
	//update the instructor profile
	@PutMapping("/update/{insId}")
	public ResponseEntity<?> updateInstructorProfile(@PathVariable int insId,
	                                                 @RequestBody Instructor updatedInstructor) {
		logger.info("upating the instructor profile");
	    try {
	        Instructor instructor = instructorService.updateInstructorProfile(insId, updatedInstructor);
	        return ResponseEntity.ok(instructor);
	    } catch (InvalidIDException e) {
	    	logger.error("error in updating profile");
	        
	        dto.setMessage(e.getMessage());
	        dto.setStatus(400);
	        return ResponseEntity.status(400).body(dto);
	    } 
	}
	//delete the instructor
	@DeleteMapping("/delete/profile")
	public ResponseEntity<?> DeleteInstructorById(@PathVariable int insId) {
		logger.info("deleting the instructor");
		try {
			//lets validate id and if valid fetch customer object
			Instructor ins = instructorService.getSingleInstructor(insId);
	          //after checking it is valid delete it 
			instructorService.DeleteinstructorById(ins);
			dto.setMessage("Instructor record hard deleted from DB!!");
			dto.setStatus(200);
			return ResponseEntity.ok(dto);
		} catch (InvalidIDException e) {
			logger.error("error deleting the instructor");
			dto.setMessage(e.getMessage());
			dto.setStatus(400);
			return ResponseEntity.status(400).body(dto); 
		}
	}
	//upload the image of the instructor
	@PostMapping("/image/upload/{Ins-id}")
	public Instructor uploadImage(@PathVariable int Insid, 
							@RequestParam MultipartFile file) throws IOException, InvalidIDException {
		
		return instructorService.uploadImage(file,Insid);
	}

}
