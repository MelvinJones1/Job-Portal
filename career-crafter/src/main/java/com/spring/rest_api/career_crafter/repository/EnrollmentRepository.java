package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}
