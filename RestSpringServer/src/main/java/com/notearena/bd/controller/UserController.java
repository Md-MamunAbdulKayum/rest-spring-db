package com.notearena.bd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notearena.bd.model.UserForm;
import com.notearena.bd.service.UserService;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/service/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserForm> getUser(@PathVariable("id") Long userId) {		
		  UserForm userForm = userService.getUser(userId);
		  if (userForm == null) {
		   return new ResponseEntity<UserForm>(HttpStatus.NOT_FOUND);
		  }
		  System.out.println("Found user: "+userForm);
		  return new ResponseEntity<UserForm>(userForm, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/service/user", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserForm>> getUserList() {
          System.out.println("All user");
		  HttpHeaders headers = new HttpHeaders();
		  List<UserForm> users = userService.getUserList();
		  if (users == null) {
		   return new ResponseEntity<List<UserForm>>(HttpStatus.NOT_FOUND);
		  }
		  headers.add("Number Of Records Found", String.valueOf(users.size()));
		  System.out.println("User found: "+users);
		  ResponseEntity<List<UserForm>> personEntity = new ResponseEntity<List<UserForm>>(users, HttpStatus.OK);
		  return  personEntity;
		
	}
	
	@RequestMapping(value = "/service/user/create", method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<UserForm> createUser(@RequestBody UserForm userObject) {
		  HttpHeaders headers = new HttpHeaders();
		  System.out.println("Started to create employee");
		  if (userObject == null) {
			  System.out.println("No user found");  
		   return new ResponseEntity<UserForm>(HttpStatus.BAD_REQUEST);
		  }
		  System.out.println("User: "+userObject);
		  System.out.println("User id: "+userObject.getUserId());
		  UserForm form = new UserForm(userObject.getUserName(), userObject.getAge(), userObject.getSalary(), userObject.getAddress());
		  userService.createUser(userObject);
		  headers.add("User Created  - ", String.valueOf(userObject.getUserId()));
		  return new ResponseEntity<UserForm>(form, headers, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/service/user/update", method = RequestMethod.PUT,produces = "application/json")
	public ResponseEntity<UserForm> updateUser(@RequestBody UserForm userObject) {
		  HttpHeaders headers = new HttpHeaders();
		  UserForm userForm = userService.getUser(userObject.getUserId());
		  if (userForm == null) {   
		   return new ResponseEntity<UserForm>(HttpStatus.NOT_FOUND);
		  } 
		  userService.updateUser(userObject);
		  headers.add("User Updated  - ", String.valueOf((userObject.getUserId())));
		  return new ResponseEntity<UserForm>(userObject, HttpStatus.OK);
		 }
	
	 @RequestMapping(value = "/service/user/delete/{id}", method = RequestMethod.DELETE)
	 public ResponseEntity<UserForm> deleteEmployee(@PathVariable("id") Long userId) {
	  HttpHeaders headers = new HttpHeaders();
	  UserForm userForm = userService.getUser(userId);
	  if (userForm == null) {   
	   return new ResponseEntity<UserForm>(HttpStatus.NOT_FOUND);
	  }
	  System.out.println("User found: "+userForm);
	  userService.deleteUser(userForm);
	  headers.add("User Deleted - ", String.valueOf(userId));
	  return new ResponseEntity<UserForm>(userForm, headers, HttpStatus.NO_CONTENT);
	 }
	
}
