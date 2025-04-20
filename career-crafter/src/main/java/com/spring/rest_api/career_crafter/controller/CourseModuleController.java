
package com.spring.rest_api.career_crafter.controller;



import com.spring.rest_api.career_crafter.model.CourseModule;


import com.spring.rest_api.career_crafter.service.CourseModuleService;



import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleService moduleService;
    org.slf4j.Logger logger =  LoggerFactory.getLogger("CourseModuleController"); 
   
    
    @PostMapping("/addmodule/course/{courseId}")
    public CourseModule addModule(@PathVariable int courseId, @RequestBody CourseModule module) {
    	logger.info("Adding module to the course");
        CourseModule savedModule = moduleService.addModuleToCourse(courseId, module);
        return savedModule;
    }

    @PutMapping("/update/{moduleId}")
    public CourseModule updateModule(@PathVariable int moduleId, @RequestBody CourseModule module) {
        logger.info("updating the course module");
        CourseModule updated = moduleService.updateCourseModule(moduleId, module);
        return updated;
    }

    @DeleteMapping("/delete/{moduleId}")
    public void deleteModule(@PathVariable int moduleId) {
        moduleService.deleteCourseModule(moduleId);
      
    }

    @GetMapping("/getmodulesPaginated/{courseId}")
    public ResponseEntity<?> getModulesByCourseId(@PathVariable int courseId,
                                                           @RequestParam int page,
                                                           @RequestParam int size) {
        logger.info("Fetching modules for course ID {} with pagination", courseId);
        Pageable pageable = PageRequest.of(page, size);
        try {
            List<CourseModule> modules = moduleService.getModulesByCourseIdPaginated(courseId, pageable);
            return ResponseEntity.ok(modules);
        } catch (Exception e) {
            logger.error("Error fetching modules: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to fetch modules: " + e.getMessage());
        }
    }
    
}
    
