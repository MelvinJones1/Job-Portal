package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.service.CourseCategoryService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService categoryService;

    Logger logger = LoggerFactory.getLogger("CourseCategoryController");

    // Add a category
    @PostMapping("/add")
    public CourseCategory addCategory(@RequestBody CourseCategory category) {
        logger.info("Adding a new category.");
        CourseCategory savedCategory = categoryService.addCategory(category);
        logger.info("Added the category successfully: {}", savedCategory);
        return savedCategory;
    }

    // Get all categories with pagination
    @GetMapping("/getAll")
    public List<CourseCategory> getAllCategories(@RequestParam int page, @RequestParam int size) {
        logger.info("Fetching all course categories with pagination.");
        Pageable pageable = PageRequest.of(page, size);
        List<CourseCategory> categories = categoryService.getAllCategories(pageable);
        logger.info("Fetched {} categories.", categories.size());
        return categories;
    }

    // Get a course category by ID
    @GetMapping("/get/{catId}")
    public CourseCategory getById(@PathVariable int catId) throws InvalidIDException {
        logger.info("Fetching category with ID: {}", catId);
        return categoryService.getById(catId);
    }

    // Update a course category
    @PutMapping("/update/{catId}")
    public CourseCategory updateTheCategory(@PathVariable int catId, @RequestBody CourseCategory updateCC) throws InvalidIDException {
        logger.info("Updating category with ID: {}", catId);
        CourseCategory updatedCategory = categoryService.updateTheCategory(catId, updateCC);
        logger.info("Updated category successfully: {}", updatedCategory);
        return updatedCategory;
    }

    // Delete a course category by ID
    @DeleteMapping("/delete/{cId}")
    public void deleteCategoryById(@PathVariable int cId) throws InvalidIDException {
        logger.info("Deleting category with ID: {}", cId);
        CourseCategory category = categoryService.getSingleCategory(cId);
        categoryService.deleteCategoryById(category);
        logger.info("Deleted category with ID: {}", cId);
    }
}