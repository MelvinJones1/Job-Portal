	package com.spring.rest_api.career_crafter.service;
	
	import java.util.Optional;

	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.spring.rest_api.career_crafter.exception.InvalidIDException;
	import com.spring.rest_api.career_crafter.exception.InvalidUsernameException;
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
	
		    public Executive createExecutive(Executive executive) throws InvalidIDException, InvalidUsernameException {
		    	User savedUser = authService.signUp(executive.getUser());
	
		        if (!savedUser.getRole().equalsIgnoreCase("EXECUTIVE")) {
		            throw new RuntimeException("User must have EXECUTIVE role.");
		        }
		        Company company = companyService.getSingleCompany(executive.getCompany().getId());
		        executive.setUser(savedUser);
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
	
	
