package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    
    private String description;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String aboutTheCourse;
    @Column(nullable = false)
    private String difficultyLevel;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String certificateAvailable;

	// private boolean isEnrolled = false;

    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAboutTheCourse() {
		return aboutTheCourse;
	}

	public void setAboutTheCourse(String aboutTheCourse) {
		this.aboutTheCourse = aboutTheCourse;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCertificateAvailable() {
		return certificateAvailable;
	}

	public void setCertificateAvailable(String certificateAvailable) {
		this.certificateAvailable = certificateAvailable;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

}
