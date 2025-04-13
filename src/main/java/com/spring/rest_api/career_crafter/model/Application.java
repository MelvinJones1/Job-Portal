package com.spring.rest_api.career_crafter.model;


import java.time.LocalDate;

import com.spring.rest_api.career_crafter.enums.ApplicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private int id;

    @ManyToOne
    private Job job; // Job being applied for

    @ManyToOne
    private JobSeeker jobSeeker; // Applicant

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status; //  APPLIED, SHORTLISTED, HIRED, REJECTED

    @Column(nullable = false)
    private LocalDate appliedAt;

   

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public JobSeeker getJobSeeker() { return jobSeeker; }
    public void setJobSeeker(JobSeeker jobSeeker) { this.jobSeeker = jobSeeker; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public LocalDate getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDate appliedAt) { this.appliedAt = appliedAt; }
}
