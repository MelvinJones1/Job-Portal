package com.spring.rest_api.career_crafter.model;


import jakarta.persistence.*;

@Entity
@Table(name = "course_modules")
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    //@JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
   // @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @Column(nullable = false)
    private int title;

    @Column(nullable = false)
    private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}