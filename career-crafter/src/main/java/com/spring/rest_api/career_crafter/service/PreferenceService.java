package com.spring.rest_api.career_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.model.Preference;
import com.spring.rest_api.career_crafter.repository.PreferenceRepository;

@Service
public class PreferenceService {

	@Autowired
	private PreferenceRepository preferenceRepository;
	
	public Preference addPrefernce(Preference preference) {
		
		return preferenceRepository.save(preference);
	}

}
