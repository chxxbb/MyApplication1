package com.example.chen.myapplication.data;

public class User {
	
	private int Id;
	
	private String Phone;
	
	private String Name;
	
	private String Password;

	private String Sex;

	private int Age;

	private String Icon;


	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}

	public String getIcon() {
		return Icon;
	}

	public void setIcon(String icon) {
		Icon = icon;
	}
	
}
