package com.spring.rest_api.career_crafter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest_api.career_crafter.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
