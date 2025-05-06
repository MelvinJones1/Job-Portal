package com.spring.rest_api.career_crafter.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class JobSeekerCertificates {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate dateGenerated;
	
	@ManyToOne
	private JobSeeker jobSeeker;
	
	
	@ManyToOne
	private Certificate certificates;
	 
	
	public Certificate getCertificates() {
		return certificates;
	}

	public void setCertificates(Certificate certificates) {
		this.certificates = certificates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateGenerated() {
		return dateGenerated;
	}

	public void setDateGenerated(LocalDate dateGenerated) {
		this.dateGenerated = dateGenerated;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	
}
