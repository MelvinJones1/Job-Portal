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
    @Column(nullable = false) // or no default
    private String category;

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(nullable = false)
    private String aboutTheCourse;

    @Column(nullable = false)
    private String difficultyLevel;

    @Column(nullable = false)
    private LocalDate createdAt;

    private boolean isEnrolled = false;

    public Course() {
        // empty constructor required by JPA
    }


    public Course(String title, String description, String aboutTheCourse,
            String difficultyLevel, CourseCategory courseCategory, Instructor instructor) {
this.title = title;
this.description = description;
this.aboutTheCourse = aboutTheCourse;
this.difficultyLevel = difficultyLevel;
this.createdAt = LocalDate.now();
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

    public Course(String title, String description, String category, String aboutTheCourse, String difficultyLevel,
			LocalDate createdAt) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.aboutTheCourse = aboutTheCourse;
		this.difficultyLevel = difficultyLevel;
		this.createdAt = createdAt;
	}

	public Course(int id, String title, String description, String category, String aboutTheCourse,
			String difficultyLevel, LocalDate createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.aboutTheCourse = aboutTheCourse;
		this.difficultyLevel = difficultyLevel;
		this.createdAt = createdAt;
	}

	public void setEnrolled(boolean isEnrolled) {
        this.isEnrolled = isEnrolled;
    }

   
}
