package com.spring.rest_api.career_crafter.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Certificate;
import com.spring.rest_api.career_crafter.model.JobSeekerCertificates;

public interface JobSeeekerCertificatesRepository extends JpaRepository<JobSeekerCertificates, Integer> {
	
	List<JobSeekerCertificates> findByCertificates(Certificate certificate);

}
