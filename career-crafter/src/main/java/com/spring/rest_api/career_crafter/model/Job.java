package com.spring.rest_api.career_crafter.model;

import java.time.LocalDate;

import com.spring.rest_api.career_crafter.enums.JobStatus;
import com.spring.rest_api.career_crafter.enums.JobType;

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
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType; // Enum: FULL_TIME, PART_TIME, CONTRACT, REMOTE, INTERNSHIP

    @Column(nullable = false)
    private String location; // On-site, Remote, Hybrid

    @Column(nullable = false)
    private String salaryRange;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String requirements;

    @Column()
    private String benefits; // Optional

    @Column(nullable = false)
    private LocalDate applicationDeadline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status; // Enum: DRAFT, PUBLISHED, CLOSED

    @ManyToOne
    private Hr hr; // HR who posted the job

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public JobType getJobType() { return jobType; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSalaryRange() { return salaryRange; }
    public void setSalaryRange(String salaryRange) { this.salaryRange = salaryRange; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public String getBenefits() { return benefits; }
    public void setBenefits(String benefits) { this.benefits = benefits; }

    public LocalDate getApplicationDeadline() { return applicationDeadline; }
    public void setApplicationDeadline(LocalDate applicationDeadline) { this.applicationDeadline = applicationDeadline; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public Hr getHr() { return hr; }
    public void setHr(Hr hr) { this.hr = hr; }
}
