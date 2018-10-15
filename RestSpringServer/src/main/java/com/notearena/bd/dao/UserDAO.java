package com.notearena.bd.dao;

import java.util.List;

import com.notearena.bd.model.UserForm;

public interface UserDAO {
	public UserForm getUser(long userId);
	public List<UserForm> getUserList();
	public String updateUser(UserForm user);
	public UserForm createUser(UserForm user);
	public String deleteUser(UserForm user);
}
