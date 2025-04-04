package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "enrolled_users")
public class EnrolledUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    //@JoinColumn(name = "user_id", nullable = false)
    private Instructor instructor;

    @ManyToOne
    //@JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private int progressPercentage = 0;

    @Column(nullable = false, updatable = false)
    private Date enrolledDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(int progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public Date getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	

	

	

}