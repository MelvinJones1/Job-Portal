package com.spring.rest_api.career_crafter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Company;
import com.spring.rest_api.career_crafter.model.Executive;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.ExecutiveRepository;

@Service
public class ExecutiveService {
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CompanyService companyService;



	  public Executive createExecutive(int userId, int companyId, Executive executive) throws InvalidIDException{
	        // 1. Fetch user
	        User user = authService.findById(userId);

	        // 2. Check if user has EXECUTIVE role
	        if (!user.getRole().equalsIgnoreCase("EXECUTIVE")) {
	            throw new RuntimeException("User is not authorized to be registered as Executive");
	        }

	        // 3. Fetch company
	        Company company = companyService.getSingleCompany(companyId);

	        // 4. Set & save
	        executive.setUser(user);
	        executive.setCompany(company);
	      
	        return executiveRepository.save(executive);
	    }



	public Executive findById(int executiveId) throws InvalidIDException{
		
		Optional<Executive> optional = executiveRepository.findById(executiveId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Executive Id is not valid....");
		}
		return optional.get();
		
	}

}


