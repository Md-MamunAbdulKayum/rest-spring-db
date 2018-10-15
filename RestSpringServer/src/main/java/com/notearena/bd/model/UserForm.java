package com.notearena.bd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserForm {

@Column
private String userName;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long userId;
@Column
private int age;
@Column
private double salary;
@Column
private String address;







/**
 * 
 */
public UserForm() {
	
}

/**
 * @param userId
 */
public UserForm(long userId) {
	super();
	this.userId = userId;
}

/**
 * @param userName
 * @param userId
 * @param age
 * @param salary
 * @param address
 */
public UserForm(String userName, long userId, int age, double salary, String address) {
	super();
	this.userName = userName;
	this.userId = userId;
	this.age = age;
	this.salary = salary;
	this.address = address;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

@Override
public String toString() {
	return "UserForm [userName=" + userName + ", userId=" + userId + ", age=" + age + ", salary=" + salary
			+ ", address=" + address + "]";
}




}
