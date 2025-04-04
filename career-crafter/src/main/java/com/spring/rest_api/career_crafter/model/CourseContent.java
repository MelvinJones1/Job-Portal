package com.spring.rest_api.career_crafter.model;


import jakarta.persistence.*;

@Entity
@Table(name = "course_content")
public class CourseContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    //@JoinColumn(name = "course_module_id", nullable = false)
    private CourseModule courseModule;

    
    
    @Column(nullable = false)
    private String contentTitle;

    @Column(nullable = false)
    private String contentUrl;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CourseModule getCourseModule() {
		return courseModule;
	}

	public void setCourseModule(CourseModule courseModule) {
		this.courseModule = courseModule;
	}


	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
}