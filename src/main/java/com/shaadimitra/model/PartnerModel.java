package com.shaadimitra.model;

import java.time.LocalDate;

public class PartnerModel {
	private String partner_id;
	private String first_name;
	private String last_name;
	private String gender;
	private int salary;
	private String religion;
	private String profession;
	private String email;
	private String number;
	private String username;
	private LocalDate dob;
	private String password;
	private String profileimage;
	
	public PartnerModel() {}
	
	// Full Constructor for Edit Profile

	public PartnerModel(String first_name, String last_name, String gender, int salary,
			String religion, String profession, String email, String number, String username, LocalDate dob) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.salary = salary;
		this.religion = religion;
		this.profession = profession;
		this.email = email;
		this.number = number;
		this.username = username;
		this.dob = dob;
	}
	


	// For Registration
	public PartnerModel(String first_name, String last_name, String gender, String email, String number,
			String username, LocalDate dob, String password, String profileImage) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.email = email;
		this.number = number;
		this.username = username;
		this.dob = dob;
		this.password = password;
		this.profileimage = profileImage;
	}

	
	// for login
	public PartnerModel(String username, String password) {
		this.username= username;
		this.password = password;
	}
	
	// Total Details
	public PartnerModel(String id, String firstName, String lastName, String gender, int salary,
			String religion, String profession, String email, String number, String userName, LocalDate dob,
			String password) {
		super();
		this.partner_id = id;
		this.first_name = firstName;
		this.last_name = lastName;
		this.gender = gender;
		this.salary = salary;
		this.religion = religion;
		this.profession = profession;
		this.email = email;
		this.number = number;
		this.username = userName;
		this.dob = dob;
		this.password = password;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileimage() {
		return profileimage;
	}
	
	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	
	
}
