
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
    private CourseCategoryRepository categoryRepository;

    // Add category
    public CourseCategory addCategory(CourseCategory category) {
        return categoryRepository.save(category);
    }

    // Get all categories with pagination
    public List<CourseCategory> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).getContent(); // Extract content as a list
    }

    // Get single category by ID
    public CourseCategory getSingleCategory(int catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Invalid Course category ID."));
    }

    // Get category by ID
    public CourseCategory getById(int catId) throws InvalidIDException {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new InvalidIDException("Course category ID is invalid."));
    }

    // Update category
    public CourseCategory updateTheCategory(int catId, CourseCategory updateCC) throws InvalidIDException {
        CourseCategory existingCategory = categoryRepository.findById(catId)
                .orElseThrow(() -> new InvalidIDException("Course category with ID " + catId + " not found."));
        existingCategory.setTitle(updateCC.getTitle()); // Update allowed fields
        return categoryRepository.save(existingCategory); // Save updated category
    }

    // Delete course category
    public void deleteCategoryById(CourseCategory category) {
        categoryRepository.delete(category);
    }
}