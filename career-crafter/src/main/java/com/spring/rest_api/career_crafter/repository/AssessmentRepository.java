package com.spring.rest_api.career_crafter.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer>{

	Assessment findByApplicationId(int appId); //Fetch assessment based on applicationId

}
