package com.spring.rest_api.career_crafter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.repository.CourseCategoryRepository;

@Service
public class CourseCategoryService {

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    // Add a new category
    public CourseCategory addCategory(CourseCategory category) {
        return courseCategoryRepository.save(category);
    }

    // Get all categories with pagination
    public List<CourseCategory> getAllCategories(Pageable pageable) {
        return courseCategoryRepository.findAll(pageable).getContent();
    }

    // Get a category by its ID
    public CourseCategory getById(int id) throws InvalidIDException {
        return courseCategoryRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Category ID " + id + " is invalid."));
    }

    // Update a category
    public CourseCategory updateTheCategory(int id, CourseCategory updatedCategory) throws InvalidIDException {
        CourseCategory existingCategory = courseCategoryRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Category ID " + id + " not found."));
        
        existingCategory.setTitle(updatedCategory.getTitle());
        return courseCategoryRepository.save(existingCategory);
    }

    // Delete a category by its ID
    public void deleteCategoryById(CourseCategory category) {
        courseCategoryRepository.delete(category);
    }

    // Get a single category for validation
    public CourseCategory getSingleCategory(int id) throws InvalidIDException {
        return courseCategoryRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Category ID " + id + " is invalid."));
    }
}