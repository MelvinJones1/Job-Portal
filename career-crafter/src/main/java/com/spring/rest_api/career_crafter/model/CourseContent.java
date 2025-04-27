		package com.spring.rest_api.career_crafter.model;
		
		
		import jakarta.persistence.*;
		
		@Entity
		public class CourseContent {
		
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private int id;
		
		    @Column(nullable = false)
		    private String contentTitle;
		
		    @Column(nullable = false)
		    private String contentUrl;
		
		    
		    @ManyToOne
		    private CourseModule courseModule;
		    
		    @ManyToOne
		    private Assignment assignment;
		
		  
			
			public Assignment getAssignment() {
				return assignment;
			}
		
			  public CourseContent() {
			    }
		
			    // Optional: parameterized constructor for easier object creation
			    public CourseContent(String contentTitle, String contentUrl, CourseModule courseModule, Assignment assignment) {
			        this.contentTitle = contentTitle;
			        this.contentUrl = contentUrl;
			        this.courseModule = courseModule;
			        this.assignment = assignment;
			    }
			public void setAssignment(Assignment assignment) {
				this.assignment = assignment;
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