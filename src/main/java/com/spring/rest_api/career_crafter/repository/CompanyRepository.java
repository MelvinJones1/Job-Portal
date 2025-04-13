package com.spring.rest_api.career_crafter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
