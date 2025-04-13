
package com.spring.rest_api.career_crafter.controller;



import com.spring.rest_api.career_crafter.model.CourseModule;

import com.spring.rest_api.career_crafter.service.CourseModuleService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleService moduleService;
 
   
    
    @PostMapping("/addmodule/course/{courseId}")
    public CourseModule addModule(@PathVariable int courseId, @RequestBody CourseModule module) {
        CourseModule savedModule = moduleService.addModuleToCourse(courseId, module);
        return savedModule;
    }

    @PutMapping("/update/{moduleId}")
    public CourseModule updateModule(@PathVariable int moduleId, @RequestBody CourseModule module) {
        CourseModule updated = moduleService.updateCourseModule(moduleId, module);
        return updated;
    }

    @DeleteMapping("/delete/{moduleId}")
    public void deleteModule(@PathVariable int moduleId) {
        moduleService.deleteCourseModule(moduleId);
      
    }

    @GetMapping("/getmodules/{courseId}")
    public List<CourseModule>getModulesByCourseId(@PathVariable int cId) {
        List<CourseModule> modules = moduleService.getModulesByCourseId(cId);
        return modules;
    }
}
    
