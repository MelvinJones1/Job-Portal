package com.spring.rest_api.career_crafter.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.CompanyReview;

public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Integer>{

	List<CompanyReview> findAllByCompanyId(int compId);

}
