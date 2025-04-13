
package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Course;

import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseModuleService {

    @Autowired
    private CourseModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseModule addModuleToCourse(int courseId, CourseModule module) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        module.setCourse(course);
        return moduleRepository.save(module);
    }
    

    public CourseModule updateCourseModule(int moduleId, CourseModule updatedModule) {
    	//check if the module is present or not 
        CourseModule existingModule = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
//update the fields
        existingModule.setTitle(updatedModule.getTitle());
        existingModule.setUrl(updatedModule.getUrl());
        return moduleRepository.save(existingModule);
    }

    public void deleteCourseModule(int moduleId) {
    	//check if module exists in the reposirtory or not
        if (!moduleRepository.existsById(moduleId)) {
            throw new RuntimeException("Module not found");
        }
        moduleRepository.deleteById(moduleId);
    }

    public List<CourseModule> getModulesByCourseId(int courseId) {
        return moduleRepository.findByCourseId(courseId);
    }
}
