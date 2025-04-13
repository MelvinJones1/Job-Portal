
package com.spring.rest_api.career_crafter.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest_api.career_crafter.dto.MessageResponseDto;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;

import com.spring.rest_api.career_crafter.model.CourseCategory;
import com.spring.rest_api.career_crafter.service.CourseCategoryService;


@RestController
@RequestMapping("/api/category")
public class CourseCategoryController {
	@Autowired
    private CourseCategoryService categoryService;

    @Autowired
    private MessageResponseDto messageResponseDto;
    org.slf4j.Logger logger =  LoggerFactory.getLogger("CourseCategoryController"); 
//add category
    @PostMapping("/add")
    public ResponseEntity<CourseCategory> addCategory(@RequestBody CourseCategory category) {
    	logger.info("adding the categories");
        CourseCategory savedCategory = categoryService.addCategory(category);
        logger.info("added the category successfully");
        return ResponseEntity.ok(savedCategory);
    }
    //get all the course categories
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategories() {
    	logger.info("fetching all the categories");
        try {
            List<CourseCategory> categories = categoryService.getAllCategories();
           logger.info("got the list of categories");
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
        	logger.error("error getting the categories");
            messageResponseDto.setMessage("Error fetching categories: " + e.getMessage());
            messageResponseDto.setStatus(500);
            return ResponseEntity.status(500).body(messageResponseDto);
        }
    }
    //get course category by id
    @GetMapping("/get/{catId}")
	public CourseCategory getById(@PathVariable int catId) throws InvalidIDException {
    	logger.info("getting categroy by Id");
		return categoryService.getById(catId);
		
	}
  //update the course category put mapping
  	@PutMapping("/update/{catId}")
  	public ResponseEntity<?> updateTheCategory(@PathVariable int catId,
              @RequestBody CourseCategory updateCC) {
  		logger.info("updating the category");
  try {
  CourseCategory CC = categoryService.updateTheCategory(catId, updateCC);
  return ResponseEntity.ok(CC);
  } catch (InvalidIDException e) {
	  logger.error("error updating the category with id"+"catid"+catId+e.getMessage());
	  messageResponseDto.setMessage(e.getMessage());
	  messageResponseDto.setStatus(400);
  return ResponseEntity.status(400).body(messageResponseDto);
  } 
  }
  	//delete the course

  	@DeleteMapping("/delete/{cId}")
  	public ResponseEntity<?> DeleteCategoyById(@PathVariable int cId) throws InvalidIDException {
  		//lets validate id and if valid fetch customer object
  		logger.info("deleting category by id");
  		CourseCategory cc = categoryService.getSingleCategory(cId);
  		  //after checking it is valid delete it 
  		categoryService.DeletecategoryById(cc);
  		messageResponseDto.setMessage("Course record hard deleted from DB!!");
  		messageResponseDto.setStatus(200);
  		return ResponseEntity.ok(messageResponseDto);
  	}
  	

}
