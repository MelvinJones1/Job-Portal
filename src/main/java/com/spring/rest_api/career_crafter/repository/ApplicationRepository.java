package com.spring.rest_api.career_crafter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByJobId(int jobId); // Fetch all applications for a job

}
