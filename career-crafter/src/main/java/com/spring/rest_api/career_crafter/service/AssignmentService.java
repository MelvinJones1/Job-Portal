package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
}
