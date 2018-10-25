package com.notearena.bd.service;

import java.util.List;

import com.notearena.bd.model.UserForm;

public interface UserService {

	public UserForm getUser(long userId);
	public List<UserForm> getUserList();
	public String updateUser(UserForm user);
	public UserForm createUser(UserForm user);
	public UserForm deleteUser(UserForm user);
}
