
package com.spring.rest_api.career_crafter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.enums.ApplicationStatus;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Application;
import com.spring.rest_api.career_crafter.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;
	
	
	public Application applyJob(Application application) {
		return applicationRepository.save(application);
	}

	public List<Application> getAllApplication() {
		return applicationRepository.findAll();
	}
	
	
	public int countApplicantsForJob(int jobId) throws InvalidIDException {

	    List<Application> applications = applicationRepository.findByJobId(jobId);

	    return applications.size(); // Count the applicants
	}

	public List<Application> getApplicationsByJob(int jobId) {
        return applicationRepository.findByJobId(jobId);
    }

	public Application findById(int applicationId)  throws InvalidIDException{
		Optional<Application> optional = applicationRepository.findById(applicationId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Application Id is not valid....");
		}
		return optional.get();
	}

	
	// HR updates the status of the application  such as "SHORTLISTED, HIRED, REJECTED, ASSSESSMENT SENT" ...
	public Application updateApplicationStatus(int applicationId, String status) throws InvalidIDException {
		
	    Application application = findById(applicationId);
	  
	    ApplicationStatus enumStatus = ApplicationStatus.valueOf(status.toUpperCase());
	    application.setStatus(enumStatus); 
	    
	    return applicationRepository.save(application);
	}

<<<<<<< Updated upstream
	// return total no. of apllications submitted by jobseeker
	public int getTotalApplications(int jsId) {
		List<Application> list = applicationRepository.findAll();
		return list.size();
	}

=======
	//JobSeeker can view the total no. applications he has submitted so far
	public int getTotalApplications(int jsId) {
		List<Application> list = applicationRepository.findByJobSeekerId(jsId);
		return list.size();
	}

	// Jobseeker can view application count based on status 
>>>>>>> Stashed changes
	public int getApplicationCountByStatus(ApplicationStatus status) {
		List<Application> list = applicationRepository.findByStatus(status);
		return list.size();
	}

<<<<<<< Updated upstream
	public  Application getSingleApplicationId(int appId) throws InvalidIDException {
		Optional<Application> optional = applicationRepository.findById(appId);
		if(optional.isEmpty())
			throw new InvalidIDException("Invalid Application Id...");
		return optional.get();
	}
	
	

=======
>>>>>>> Stashed changes

}
