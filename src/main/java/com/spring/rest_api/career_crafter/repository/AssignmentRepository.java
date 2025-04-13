package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
