package com.spring.rest_api.career_crafter.model;
import jakarta.persistence.*;




@Entity
@Table(name = "certification")
public class Certificates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
   // @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String certificateUrl;

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

	public String getCertificateUrl() {
		return certificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}
	
}