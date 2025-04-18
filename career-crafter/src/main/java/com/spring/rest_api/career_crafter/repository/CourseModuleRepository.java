
package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.CourseModule;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Integer> {
	Page<CourseModule> findByCourseId(int courseId, Pageable pageable);
	List<CourseModule> findByCourseId(int courseId);
}
	