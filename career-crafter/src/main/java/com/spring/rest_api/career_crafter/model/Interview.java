package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Application application; //  Interview is linked to a job application

    @ManyToOne
    private Executive executive; //  Who is conducting the interview

    @Column(nullable = false)
    private String round; // Round 1, Round 2, Final Round, etc.

    @Column(nullable = false)
    private String status; // Scheduled, Completed, Cancelled

    @Column(nullable = true)
    private String feedback; // Interviewer's feedback

    @Column(nullable = false)
    private String meetingLink; // ✅ Google Meet / Zoom / Teams link for the interview

    // Getters and Setters
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

    public Executive getExecutive() { 
    	return executive; 
    	}
    public void setExecutive(Executive executive) {
    	this.executive = executive;
    	}

    public String getRound() {
    	return round; 
    	}
    public void setRound(String round) { 
    	this.round = round;
    	}

    public String getStatus() {
    	return status; 
    	}
    public void setStatus(String status) {
    	this.status = status;
    	}

    public String getFeedback() {
    	return feedback;
    	}
    public void setFeedback(String feedback) {
    	this.feedback = feedback;
    	}

    public String getMeetingLink() {
    	return meetingLink;
    	}
    public void setMeetingLink(String meetingLink) {
    	this.meetingLink = meetingLink; 
    	}
}
