package com.spring.rest_api.career_crafter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
