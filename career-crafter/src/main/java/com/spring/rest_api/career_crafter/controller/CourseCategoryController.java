
package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.spring.rest_api.career_crafter.exception.InvalidIDException;

import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.service.CourseCategoryService;

@RestController
@RequestMapping("/api/category")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService categoryService;

    Logger logger = LoggerFactory.getLogger("CourseCategoryController");

    // Add category
    @PostMapping("/add")
    public CourseCategory addCategory(@RequestBody CourseCategory category) {
        logger.info("Adding a new category.");
        CourseCategory savedCategory = categoryService.addCategory(category);
        logger.info("Added the category successfully: {}", savedCategory);
        return savedCategory; // Returning the created category directly
    }

    // Get all categories with pagination
    @GetMapping("/getAll")
    public List<CourseCategory> getAllCategories(@RequestParam int page, @RequestParam int size) {
        logger.info("Fetching all course categories with pagination.");
        Pageable pageable = PageRequest.of(page, size);
        List<CourseCategory> categories = categoryService.getAllCategories(pageable);
        logger.info("Fetched {} categories.", categories.size());
        return categories; // Returning the list of categories directly
    }

    // Get course category by ID
    @GetMapping("/get/{catId}")
    public CourseCategory getById(@PathVariable int catId) throws InvalidIDException {
        logger.info("Fetching category with ID: {}", catId);
        return categoryService.getById(catId); // Throws InvalidIDException if not found
    }

    // Update the course category
    @PutMapping("/update/{catId}")
    public CourseCategory updateTheCategory(@PathVariable int catId, @RequestBody CourseCategory updateCC) throws InvalidIDException {
        logger.info("Updating category with ID: {}", catId);
        CourseCategory updatedCategory = categoryService.updateTheCategory(catId, updateCC);
        logger.info("Updated category successfully: {}", updatedCategory);
        return updatedCategory; // Returning the updated category directly
    }

    // Delete the course category by ID
    @DeleteMapping("/delete/{cId}")
    public void deleteCategoryById(@PathVariable int cId) throws InvalidIDException {
        logger.info("Deleting category with ID: {}", cId);
        CourseCategory category = categoryService.getSingleCategory(cId);
        categoryService.deleteCategoryById(category); // Exception is thrown if ID is invalid
        logger.info("Deleted category with ID: {}", cId);
    }
}