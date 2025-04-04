package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    private JobSeeker jobSeeker; // Candidate who applied

    @ManyToOne
    private Job job; // Job applied for

    @Column(nullable = false)
    private String status; // Pending, Rejected, Hired

    @Column(nullable = true)
    private Double assessmentScore; // Score after assessment

    @ManyToOne
    private Hr hr; // HR handling this application

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public JobSeeker getJobSeeker() {
//		return jobSeeker;
//	}
//
//	public void setJobSeeker(JobSeeker jobSeeker) {
//		this.jobSeeker = jobSeeker;
//	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAssessmentScore() {
		return assessmentScore;
	}

	public void setAssessmentScore(Double assessmentScore) {
		this.assessmentScore = assessmentScore;
	}

	public Hr getHr() {
		return hr;
	}

	public void setHr(Hr hr) {
		this.hr = hr;
	}

    // Getters and Setters
    
    
}
