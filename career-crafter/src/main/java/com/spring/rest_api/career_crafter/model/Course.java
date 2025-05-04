package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String aboutTheCourse;

    @Column(nullable = false)
    private String difficultyLevel;

   
    private LocalDate createdAt;

    private boolean isEnrolled = false;

    // Category name directly in Course
    @Column(nullable = false)
    private String category;
    @OneToOne
    private Assignment assignment;

    // Getters and Setters

    public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public String getCategor() {
        return category;
    }

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isEnrolled() {
		return isEnrolled;
	}

	public void setEnrolled(boolean isEnrolled) {
		this.isEnrolled = isEnrolled;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setCategoryName(String category) {
        this.category = category;
    }

    // Other fields and getters/setters...
}
