
package com.spring.rest_api.career_crafter.service;

import java.io.IOException;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	
	
		//get the instructor profile while logged in
		public Instructor getInstructorProfile(String username)
		{
			
			User user = authRepository.findByUsername(username);
		        return instructorRepository.findByUser(user);
		}
		
		//save the instructor profile

    public Instructor createInstructorProfile(Instructor instructor, String username) {
        User user = authRepository.findByUsername(username);
        instructor.setUser(user);
        return instructorRepository.save(instructor);
    }

    //get single Instructor
    public Instructor getSingleInstructor(int id) throws InvalidIDException{
		Optional<Instructor> optional =  instructorRepository.findById(id);
		if(optional.isEmpty())
			throw new InvalidIDException(" instructor ID given is Invalid...");
		return optional.get();
	}
  //updtae the instructor

  	public Instructor updateInstructorProfile(int insId, Instructor Newins) throws InvalidIDException {
  	    // Check if the instructor exists
  	    Instructor Oldins = instructorRepository.findById(insId)
  	            .orElseThrow(() -> new InvalidIDException("Instructor with ID " + insId + " not found"));

  	    // Update fields  only those allowed to change
  	    Oldins.setFirstName(Newins.getFirstName());
  	    Oldins.setLastName(Newins.getLastName());
  	    Oldins.setEmail(Newins.getEmail());
  	    Oldins.setMobileNumber(Newins.getMobileNumber());
  	    Oldins.setProfileImagePath(Newins.getProfileImagePath());
  	    Oldins.setCertifications(Newins.getCertifications());
  	    // Save the updated instructor
  	    return instructorRepository.save(Oldins);
  	}
  	
  	//delete the instructor

  	public void DeleteinstructorById(Instructor ins) {
  		 instructorRepository.delete(ins);
  		
  	}

	public Instructor uploadImage(MultipartFile file,int Insid) throws IOException, InvalidIDException {
		/*check if Ins-id is valid  if not there throws exception*/
		Instructor instructor = instructorRepository.findById(Insid)
				.orElseThrow(()->new InvalidIDException("Invalid INS-ID given.."));
		//allowed formats of the image 
		List<String> allowedExtensions = Arrays.asList("png","jpg","jpeg","gif","svg"); 
		String originalFileName = file.getOriginalFilename(); 
		System.out.println(originalFileName);
		String extension= originalFileName.split("\\.")[1];
		/*Check weather extension is allowed or not */
		if( !(allowedExtensions.contains(extension))) {
			throw new RuntimeException("Image Type Invalid");
		}
		
		String uploadPath= "C:\\Users\\ragip\\OneDrive\\Documents\\JAVA FULL STACK HEX\\hexawareproject\\career-crafter\\uploads";
		
		/*Create directory *///Check if directory is present else create it
		Files.createDirectories(Paths.get(uploadPath));
		/*Define full path with folder and image name */
		Path path = Paths.get(uploadPath + "\\" +originalFileName); 
		/*Copy the image into uploads path */
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		/*Save this path in Database */
		instructor.setProfileImagePath(path.toString());
		return instructorRepository.save(instructor);
	}



	//create profile, get profile,delete ,updtae profile and upload image are the apis done for the instructor module
		//we used the built in jpa methods
}
