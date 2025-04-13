package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.model.CourseContent;
import com.spring.rest_api.career_crafter.model.CourseModule;
import com.spring.rest_api.career_crafter.repository.AssignmentRepository;
import com.spring.rest_api.career_crafter.repository.CourseContentRepository;
import com.spring.rest_api.career_crafter.repository.CourseModuleRepository;
import com.spring.rest_api.career_crafter.exception.InvalidIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseContentService {

    @Autowired
    private CourseContentRepository contentRepository;

    @Autowired
    private CourseModuleRepository moduleRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    public CourseContent addContent(CourseContent content, int moduleId, Integer assignmentId) throws InvalidIDException {
        CourseModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new InvalidIDException("Invalid Module ID: " + moduleId));

        content.setCourseModule(module);

        if (assignmentId != null) {
            Assignment assignment = assignmentRepository.findById(assignmentId)
                    .orElseThrow(() -> new InvalidIDException("Invalid Assignment ID: " + assignmentId));
            content.setAssignment(assignment);
        }

        return contentRepository.save(content);
    }
}
