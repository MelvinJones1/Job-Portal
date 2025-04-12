package com.spring.rest_api.career_crafter.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Assessment {

    public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String assessmentLink; // Online test link

    @Column(nullable = false)
    private boolean completed; // True if candidate finished it

    @Column(nullable = true)
    private Double score; // Score assigned by HR
    
    @ManyToOne
    private Application application;




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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