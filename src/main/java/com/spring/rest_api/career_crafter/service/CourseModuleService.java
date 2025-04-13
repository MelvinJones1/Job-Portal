package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Course;
import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.repository.CourseRepository;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseModuleService {

    @Autowired
    private CourseModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseModule addModule(CourseModule module, int courseId) throws InvalidIDException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidIDException("Invalid Course ID: " + courseId));

        module.setCourse(course);
        return moduleRepository.save(module);
    }
}
