package com.spring.rest_api.career_crafter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Preference;
import com.spring.rest_api.career_crafter.repository.PreferenceRepository;

@Service
public class PreferenceService {

	@Autowired
	private PreferenceRepository preferenceRepository;
	
	public Preference addPrefernce(Preference preference) {
		
		return preferenceRepository.save(preference);
	}

	public Preference findById(int preferenceId)throws InvalidIDException {

		Optional<Preference> optional = preferenceRepository.findById(preferenceId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Preference Id is not valid....");
		}
		return optional.get();
	}

}
