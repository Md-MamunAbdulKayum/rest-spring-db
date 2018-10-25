package com.notearena.bd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notearena.bd.dao.UserDAO;
import com.notearena.bd.model.UserForm;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO; 
	
	public UserForm getUser(long userId) {
		// TODO Auto-generated method stub
		return userDAO.getUser(userId);
	}

	public List<UserForm> getUserList() {
		// TODO Auto-generated method stub
		return userDAO.getUserList();
	}

	public String updateUser(UserForm user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(user);
	}

	public UserForm createUser(UserForm user) {
		// TODO Auto-generated method stub
		return userDAO.createUser(user);
	}

	public UserForm deleteUser(UserForm user) {
		// TODO Auto-generated method stub
		return userDAO.deleteUser(user);
	}

}
