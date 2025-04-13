package com.spring.rest_api.career_crafter.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Company;
import com.spring.rest_api.career_crafter.model.Hr;
import com.spring.rest_api.career_crafter.model.User;
import com.spring.rest_api.career_crafter.repository.HrRepository;

@Service
public class HrService {
	
	@Autowired
	private HrRepository hrRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CompanyService companyService;

	public Hr findById(int hrId) throws InvalidIDException {
	
		Optional<Hr> optional = hrRepository.findById(hrId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Hr Id is not valid....");
		}
		return optional.get();
		
		
	}

	

	
	public Hr createHr(int userId, int companyId, Hr hr) throws InvalidIDException{
	    User user = authService.findById(userId);

	    if (!user.getRole().equalsIgnoreCase("HR")) {
	        throw new RuntimeException("User role is not HR");
	    }

	    Company company = companyService.getSingleCompany(companyId);
	           

	    hr.setUser(user);
	    hr.setCompany(company);
	    hr.setCreatedAt(LocalDate.now());
	    return hrRepository.save(hr);
	}

	
	

}
