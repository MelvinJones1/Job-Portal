package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.model.Preference;
import com.spring.rest_api.career_crafter.service.PreferenceService;

@RestController
@RequestMapping("/api/preference")
public class PreferenceController {
	
	@Autowired
	private PreferenceService preferenceService;
	
	@PostMapping("/add")
	public Preference addPrefernce(@RequestBody Preference preference) {
		return preferenceService.addPrefernce(preference);
	}

}
