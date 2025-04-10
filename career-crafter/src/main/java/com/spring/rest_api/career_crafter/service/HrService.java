package com.spring.rest_api.career_crafter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Hr;
import com.spring.rest_api.career_crafter.repository.HrRepository;

@Service
public class HrService {
	
	@Autowired
	private HrRepository hrRepository;

	public Hr findById(int hrId) throws InvalidIDException {
	
		Optional<Hr> optional = hrRepository.findById(hrId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Hr Id is not valid....");
		}
		return optional.get();
		
		
	}
	
	

}
