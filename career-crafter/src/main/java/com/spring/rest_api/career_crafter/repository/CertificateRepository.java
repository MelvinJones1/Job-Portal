
package com.spring.rest_api.career_crafter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.model.CourseContent;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    List<Certificate> findByCourseContentId(int contentId);
    List<Certificate> findByCourseContentIn(List<CourseContent> contents);
    Page<Certificate> findByCourseContentId(int contentId, Pageable pageable);
}

