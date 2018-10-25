package com.notearena.bd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserForm {


private String userName;


private long userId;

private int age;

private double salary;

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
@JsonCreator
public UserForm(@JsonProperty("userName") String userName, @JsonProperty("userId") long userId,@JsonProperty("age") int age,@JsonProperty("salary") double salary, @JsonProperty("address") String address) {
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

//@Override
//public String toString() {
//	return "UserForm [userName=" + userName + ", userId=" + userId + ", age=" + age + ", salary=" + salary
//			+ ", address=" + address + "]";
//}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("userName=");
	builder.append(userName);
	builder.append(", userId=");
	builder.append(userId);
	builder.append(", age=");
	builder.append(age);
	builder.append(", salary=");
	builder.append(salary);
	builder.append(", address=");
	builder.append(address);
	return builder.toString();
}


}
