
package com.spring.rest_api.career_crafter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.CourseCategory;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {
	Page<CourseCategory> findAll(Pageable pageable);
}
