
package com.spring.rest_api.career_crafter.service;

import com.spring.rest_api.career_crafter.model.Enrollment;
import com.spring.rest_api.career_crafter.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Get all enrollments with pagination
    public List<Enrollment> getAllEnrollmentsPaginated(Pageable pageable) {
        return enrollmentRepository.findAll(pageable).getContent(); // Extract content as List
    }
    public List<Enrollment> getEnrollmentsByCategory(String categoryName) {
        return enrollmentRepository.findByCourseCategoryTitle(categoryName); // Fetch enrollments by category title
    }

    // Get enrollments by job seeker name
    public List<Enrollment> getEnrollmentsByJobSeekerName(String name) {
        return enrollmentRepository.findByJobSeekerName(name); // Fetch enrollments by job seeker name
    }

    // Get total number of enrollments
    public long getTotalEnrollments() {
        return enrollmentRepository.count(); // Count total enrollments
    }

    // Get count of completed enrollments
    public long getCompletedEnrollments() {
        return enrollmentRepository.countByCompleted(true); // Count completed enrollments
    }
}