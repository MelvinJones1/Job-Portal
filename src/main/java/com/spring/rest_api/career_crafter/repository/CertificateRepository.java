package com.spring.rest_api.career_crafter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest_api.career_crafter.model.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    List<Certificate> findByCourseContentId(int contentId);
}
