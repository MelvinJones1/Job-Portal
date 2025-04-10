package com.spring.rest_api.career_crafter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Company;
import com.spring.rest_api.career_crafter.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}

	public Company getSingleCompany(int compId) {
		Optional<Company> optional = companyRepository.findById(compId);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Company ID...");
		return optional.get();
	}

}
