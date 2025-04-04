package com.spring.rest_api.career_crafter.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
  //  @JoinColumn(name = "course_module_id", nullable = false)
    private CourseModule courseModule;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private LocalDate submissionDeadline;

	@ManyToOne
	private CourseContent courseContent;

	

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CourseContent getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(CourseContent courseContent) {
		this.courseContent = courseContent;
	}

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

	public LocalDate getSubmissionDeadline() {
		return submissionDeadline;
	}

	public void setSubmissionDeadline(LocalDate submissionDeadline) {
		this.submissionDeadline = submissionDeadline;
	}

}