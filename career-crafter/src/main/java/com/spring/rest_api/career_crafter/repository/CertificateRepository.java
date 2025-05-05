package com.spring.rest_api.career_crafter.repository;

import com.spring.rest_api.career_crafter.model.Assignment;
import com.spring.rest_api.career_crafter.model.Certificate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
  
}

