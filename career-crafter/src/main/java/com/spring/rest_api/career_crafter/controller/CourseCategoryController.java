package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.dto.MessageResponseDto;
import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.service.CourseCategoryService;

@RestController
@RequestMapping("/api/category")
public class CourseCategoryController {
	@Autowired
    private CourseCategoryService categoryService;

    @Autowired
    private MessageResponseDto messageResponseDto;

    @PostMapping("/add")
    public ResponseEntity<CourseCategory> addCategory(@RequestBody CourseCategory category) {
        CourseCategory savedCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(savedCategory);
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllCategories() {
        try {
            List<CourseCategory> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            messageResponseDto.setMessage("Error fetching categories: " + e.getMessage());
            messageResponseDto.setStatus(500);
            return ResponseEntity.status(500).body(messageResponseDto);
        }
    }

}
