
package com.spring.rest_api.career_crafter.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.spring.rest_api.career_crafter.model.Instructor;
import com.spring.rest_api.career_crafter.model.User;

public interface InstructorRepository  extends JpaRepository<Instructor, Integer>{

	Instructor findByUser(User user);
	//findAll,save(),
}
