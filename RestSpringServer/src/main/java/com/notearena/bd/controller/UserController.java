package com.notearena.bd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notearena.bd.model.UserForm;
import com.notearena.bd.service.UserService;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "users")
	public void getDashboard(@ModelAttribute("userObject") UserForm userObject, Model model) {
		List<UserForm> users = userService.getUserList();
		System.out.println("Check dashboard: "+users);
		
	}
	
}
