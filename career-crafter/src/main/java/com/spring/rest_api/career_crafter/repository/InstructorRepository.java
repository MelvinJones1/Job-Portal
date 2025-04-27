
package com.spring.rest_api.career_crafter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest_api.career_crafter.model.Instructor;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor findByUserUsername(String username);
}
