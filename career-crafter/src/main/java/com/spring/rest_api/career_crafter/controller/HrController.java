package com.spring.rest_api.career_crafter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.Hr;
import com.spring.rest_api.career_crafter.service.HrService;

@RestController
@RequestMapping("/api/hr")
public class HrController {
	
	
	@Autowired
	private HrService hrService;
	
	@PostMapping("/add/{userId}/{companyId}")
    public Hr createHr(@PathVariable int userId,
                       @PathVariable int companyId,
                       @RequestBody Hr hr)  throws InvalidIDException{
		
        return hrService.createHr(userId, companyId, hr);
        
    }
	
	
	

}
