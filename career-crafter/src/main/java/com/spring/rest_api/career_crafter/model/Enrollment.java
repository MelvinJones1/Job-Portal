package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private JobSeeker jobSeeker;
//
    @ManyToOne
    private Course course;

   

    private boolean completed;

    private LocalDate enrolledDate;

    private LocalDate completionDate; // Optional for tracking completion

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public JobSeeker getJobSeeker() { return jobSeeker; }
    public void setJobSeeker(JobSeeker jobSeeker) { this.jobSeeker = jobSeeker; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

  

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDate getEnrolledDate() { return enrolledDate; }
    public void setEnrolledDate(LocalDate enrolledDate) { this.enrolledDate = enrolledDate; }

    public LocalDate getCompletionDate() { return completionDate; }
    public void setCompletionDate(LocalDate completionDate) { this.completionDate = completionDate; }
}