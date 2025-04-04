package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "assessments")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Application application; // Assessment is linked to a job application

    @Column(nullable = false)
    private String assessmentLink; // Online test link

    @Column(nullable = false)
    private boolean completed; // True if candidate finished it

    @Column(nullable = true)
    private Double score; // Score assigned by HR

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getAssessmentLink() {
		return assessmentLink;
	}

	public void setAssessmentLink(String assessmentLink) {
		this.assessmentLink = assessmentLink;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

    // Getters and Setters
    
    
}
