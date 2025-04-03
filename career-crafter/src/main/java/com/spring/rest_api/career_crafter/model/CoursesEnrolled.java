package com.spring.rest_api.career_crafter.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CoursesEnrolled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate dateEnrolled;
	
	@ManyToOne
	private JobSeeker jobSeeker;
	
	/*@ManyToOne
	private Courses courses;*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateEnrolled() {
		return dateEnrolled;
	}

	public void setDateEnrolled(LocalDate dateEnrolled) {
		this.dateEnrolled = dateEnrolled;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	
	//private Courses courses;
	
	
}
