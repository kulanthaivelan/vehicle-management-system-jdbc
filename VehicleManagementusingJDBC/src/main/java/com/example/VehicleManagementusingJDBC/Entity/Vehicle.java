package com.example.VehicleManagementusingJDBC.Entity;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
//
//public class Vehicle {
//	private Integer id;
//	private String make;
//	private String model;
//	private Integer year;
//	private String color;
//	private LocalDateTime createdAt;
//
//	// Constructors
//	public Vehicle() {
//	} // Required for Thymeleaf
//
//	public Vehicle(Integer id, String make, String model, Integer year, String color) {
//		this.id = id;
//		this.make = make;
//		this.model = model;
//		this.year = year;
//		this.color = color;
//	}
//
//	// Getters and Setters
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getMake() {
//		return make;
//	}
//
//	public void setMake(String make) {
//		this.make = make;
//	}
//
//	public String getModel() {
//		return model;
//	}
//
//	public void setModel(String model) {
//		this.model = model;
//	}
//
//	public Integer getYear() {
//		return year;
//	}
//
//	public void setYear(Integer year) {
//		this.year = year;
//	}
//
//	public String getColor() {
//		return color;
//	}
//
//	public void setColor(String color) {
//		this.color = color;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//}
public class Vehicle {
    private int id;
    private String registrationNo;
    private String make;
    private String model;
    private int year;
    // getters and setters
    
	public Vehicle(int id, String registrationNo, String make, String model, int year) {
		super();
		this.id = id;
		this.registrationNo = registrationNo;
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	public Vehicle() {
	
	}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
    
    
    
}
