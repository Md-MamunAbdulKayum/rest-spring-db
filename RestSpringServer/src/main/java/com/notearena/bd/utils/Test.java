package com.notearena.bd.utils;

import com.notearena.bd.dao.UserDAO;
import com.notearena.bd.dao.UserDAOImpl;
import com.notearena.bd.service.UserService;
import com.notearena.bd.service.UserServiceImpl;

public class Test {

	
	public static void main(String[] args){

UserDAO userDao = new UserDAOImpl();
		
		System.out.println("Users: "+userDao.getUser(1));
	    }
		
	    
}
