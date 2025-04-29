	package com.spring.rest_api.career_crafter.model;
	
	import jakarta.persistence.*;
	
	
	@Entity
	@Table(name = "course_instructor")
	public class Instructor {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(nullable = false)
	    private String firstName;
	    @Column(nullable = false)
	    private String lastName;
	    @Column(nullable = false)
	    private String email;
	    
	    private String mobileNumber;

	    @Column(nullable = true)  // Nullable allows null values in the DB
	    private String highestQualification;

	    private String certifications;
	    
	    @Column(nullable = true)
	    private String profileImagePath;

	    @OneToOne
	    private User user;

	    // Getters and Setters

	    public String getHighestQualification() {
	        return highestQualification;
	    }

	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getCertifications() {
			return certifications;
		}

		public void setCertifications(String certifications) {
			this.certifications = certifications;
		}

		public String getProfileImagePath() {
			return profileImagePath;
		}

		public void setProfileImagePath(String profileImagePath) {
			this.profileImagePath = profileImagePath;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public void setHighestQualification(String highestQualification) {
	        this.highestQualification = highestQualification;
	    }

	    // Other fields and methods
	}
