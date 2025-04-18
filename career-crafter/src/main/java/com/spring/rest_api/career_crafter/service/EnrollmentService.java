
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

    public List<Enrollment> getAllEnrollmentsPaginated(Pageable pageable) {
        return enrollmentRepository.findAll(pageable).getContent(); // Extract content as List
    }
    public List<Enrollment> getEnrollmentsByCategory(String category) {
        return enrollmentRepository.findByCourseCategory(category);
    }

    public List<Enrollment> getEnrollmentsByJobSeekerName(String name) {
        return enrollmentRepository.findByJobSeekerName(name);
    }
    public long getTotalEnrollments() {
        return enrollmentRepository.count();
    }

    public long getCompletedEnrollments() {
        return enrollmentRepository.countByCompleted(true);
    }
}
