package com.example.chen.myapplication.data;

public class Doctor {
	
	private Integer id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private Integer title;
	
	private Integer section;
	
	private Integer hospital;
	
	private String adept;
	
	private String bio;
	
	private String icon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Integer getHospital() {
		return hospital;
	}

	public void setHospital(Integer hospital) {
		this.hospital = hospital;
	}

	public String getAdept() {
		return adept;
	}

	public void setAdept(String adept) {
		this.adept = adept;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", title="
				+ title + ", section=" + section + ", hospital=" + hospital + ", adept=" + adept + ", bio=" + bio
				+ ", icon=" + icon + "]";
	}
	
}
