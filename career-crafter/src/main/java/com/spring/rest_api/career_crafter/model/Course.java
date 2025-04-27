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

    @Column(nullable = false)
    private LocalDate createdAt;

    private boolean isEnrolled = false;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CourseCategory category;

    // Getters and Setters
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

    public CourseCategory getCategory() {
        return category;
    }

    public void setCategory(CourseCategory category) {
        this.category = category;
    }

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(int id, String title, String description, String aboutTheCourse, String difficultyLevel,
			LocalDate createdAt, boolean isEnrolled, CourseCategory category) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.aboutTheCourse = aboutTheCourse;
		this.difficultyLevel = difficultyLevel;
		this.createdAt = createdAt;
		this.isEnrolled = isEnrolled;
		this.category = category;
	}

	public Course(String title, String description, String aboutTheCourse, String difficultyLevel, LocalDate createdAt,
			boolean isEnrolled, CourseCategory category) {
		super();
		this.title = title;
		this.description = description;
		this.aboutTheCourse = aboutTheCourse;
		this.difficultyLevel = difficultyLevel;
		this.createdAt = createdAt;
		this.isEnrolled = isEnrolled;
		this.category = category;
	}
}