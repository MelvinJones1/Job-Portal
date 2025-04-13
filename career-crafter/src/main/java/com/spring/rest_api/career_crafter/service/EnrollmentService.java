package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
