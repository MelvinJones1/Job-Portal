
package com.spring.rest_api.career_crafter.repository;



import com.spring.rest_api.career_crafter.model.CourseContent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseContentRepository extends JpaRepository<CourseContent, Integer> {
    List<CourseContent> findByCourseModuleId(int moduleId);
    Page<CourseContent> findByCourseModuleId(int moduleId, Pageable pageable);
}
