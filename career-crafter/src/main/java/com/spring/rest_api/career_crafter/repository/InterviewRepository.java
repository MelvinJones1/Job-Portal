package com.spring.rest_api.career_crafter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
	List<Interview> findByExecutiveId(int executiveId);

}
