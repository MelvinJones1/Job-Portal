
package com.spring.rest_api.career_crafter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.rest_api.career_crafter.exception.InvalidIDException;

import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.repository.CourseCategoryRepository;

@Service
public class CourseCategoryService {
    @Autowired
    private CourseCategoryRepository categoryRepository;

    

    public CourseCategory addCategory(CourseCategory category) {
        return categoryRepository.save(category);
    }
    
    public List<CourseCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    
	public CourseCategory getSingleCategory(int catId) {
		Optional<CourseCategory> optional =categoryRepository.findById(catId);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Course category  Id");
		return optional.get();
	}
	
	//get by course id
public CourseCategory getById(int catId) throws InvalidIDException {
		
		Optional<CourseCategory> optional = categoryRepository.findById(catId);
		if(optional.isEmpty())
			throw new InvalidIDException("Course ID Invalid..");
		
		return optional.get();
	}

    

		//update the category
			public CourseCategory updateTheCategory(int catId, CourseCategory updateCC) throws InvalidIDException {
		  	    // Check if the course category exists
		  	    CourseCategory OldCC = categoryRepository.findById(catId)
		  	            .orElseThrow(() -> new InvalidIDException("Course category with ID " + catId + " not found"));

		  	    // Update fields  only those allowed to change
		  	   OldCC.setTitle(updateCC.getTitle());
		  	   return categoryRepository.save(OldCC);
		  	}
		//delete the course categroy 
			public void DeletecategoryById(CourseCategory cc) {

		                     categoryRepository.delete(cc);
				
			}
    
}
