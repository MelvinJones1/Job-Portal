
package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.Enrollment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	List<Enrollment> findByCourseCategory(String category);
	List<Enrollment> findByJobSeekerName(String name);
}
