package com.spring.rest_api.career_crafter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
