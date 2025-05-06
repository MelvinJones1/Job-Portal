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
	
		    @Column(nullable = false)
		    private String category;
	
		    @PrePersist
		    protected void onCreate() {
		        this.createdAt = LocalDate.now();
		    }
	
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
	
		    public void setEnrolled(boolean enrolled) {
		        isEnrolled = enrolled;
		    }
	
		    public String getCategory() {
		        return category;
		    }
	
		    public void setCategory(String category) {
		        this.category = category;
		    }
		}
