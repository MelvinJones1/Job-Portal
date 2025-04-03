package com.spring.rest_api.career_crafter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Preferences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String desiredJobTitle;
	
	private double desiredMinSalary;
	
	private String desiredLocation;
	
	private String desiredJobType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesiredJobTitle() {
		return desiredJobTitle;
	}

	public void setDesiredJobTitle(String desiredJobTitle) {
		this.desiredJobTitle = desiredJobTitle;
	}

	public double getDesiredMinSalary() {
		return desiredMinSalary;
	}

	public void setDesiredMinSalary(double desiredMinSalary) {
		this.desiredMinSalary = desiredMinSalary;
	}

	public String getDesiredLocation() {
		return desiredLocation;
	}

	public void setDesiredLocation(String desiredLocation) {
		this.desiredLocation = desiredLocation;
	}

	public String getDesiredJobType() {
		return desiredJobType;
	}

	public void setDesiredJobType(String desiredJobType) {
		this.desiredJobType = desiredJobType;
	}
	
	
}
