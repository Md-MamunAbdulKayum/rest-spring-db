package com.notearena.bd.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.notearena.bd.model.UserForm;

@Controller
public class UserController {
	private List<UserForm> employees;

	@RequestMapping(value = "createuser")
public void createUser() throws Exception {
		
		System.out.println("At createuser controller");
		
		try{
		
		RestTemplate restTemplate = new RestTemplate();

		String uri = "http://localhost:8080/SpringRestServer/service/user/create";

		UserForm userForm = new UserForm("Dada", 3, 29, 1000, "Test address");

		
		ResponseEntity<UserForm> personEntity = null;
		try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
			  // Data attached to the request.
	        HttpEntity<UserForm> entity = new HttpEntity<UserForm>(userForm, headers);
	        personEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, UserForm.class); 
	        System.out.println("Address:"+personEntity.getBody().getAddress());
		} catch (HttpStatusCodeException exception) {
		    int statusCode = exception.getStatusCode().value();
		    System.out.println("error statusCode: "+statusCode);  
		}
		 
	     if(personEntity!=null) {
			 System.out.println("Result - status ("+ personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());
		        System.out.println("Create user service result: "+personEntity.getBody().toString()); 

	     }    else {
	    	 System.out.println("null value"); 

	     }


			
	}catch( Exception e){
		
		System.out.println("Exception occured: "+e);
	}	 
        
  
		
	}
	
	@RequestMapping(value = "getuser", method = RequestMethod.GET)
	public String getUser(ModelMap model) throws Exception {
			
			System.out.println("At getuser controller");
			
			try{
			
			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://localhost:8080/SpringRestServer/service/user/{id}";

			//UserForm userForm = new UserForm("Dada", 4, 29, 10000000.00, "Test address");

			
			
			// UserForm response = null;
			 ResponseEntity<UserForm> personEntity = null;
			try {

			  
			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.APPLICATION_JSON);
			        HttpEntity<String> entity = new HttpEntity<String>("", headers);
			        personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, UserForm.class, 1);
			        System.out.println("Address:"+personEntity.getBody().getAddress());

			     
			} catch (HttpStatusCodeException exception) {
			    int statusCode = exception.getStatusCode().value();
			    System.out.println("error statusCode: "+statusCode); 
			}
			 
		     if(personEntity!=null) {
				// System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
			        System.out.println("Get particular user service result: "+personEntity.toString()); 
			        System.out.println("User name: "+personEntity.getBody().getUserName()); 
			        model.addAttribute("user", personEntity.getBody());
			        model.addAttribute("name", personEntity.getBody().getUserName());
                   return "userInfo";
		     }    else {
		    	 System.out.println("null value"); 
		    	 return "error";
		     }


				
		}catch( Exception e){
			
			System.out.println("Exception occured: "+e);
			return "error";
		}	 
	        
	  
			
		}
	
	@RequestMapping(value = "getuserlist")
	public String getUsersList(ModelMap model) throws Exception {
		
			System.out.println("At getuserlist controller");
			
			try{
			
			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://localhost:8080/SpringRestServer/service/user/";

			//UserForm userForm = new UserForm("Dada", 4, 29, 10000000.00, "Test address");

			
			
			ResponseEntity<List<UserForm>> personEntity = null;
			try {

		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        HttpEntity<String> entity = new HttpEntity<String>("", headers);
		        System.out.println("Getting list");
		        //personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserForm>>() {});
		        personEntity = restTemplate.exchange(
		        		  uri,
		        		  HttpMethod.GET,
		        		  null,
		        		  new ParameterizedTypeReference<List<UserForm>>(){});
		        System.out.println("Address:"+personEntity.getBody().get(0).getAddress());

						
			     
			} catch (HttpStatusCodeException exception) {
			    int statusCode = exception.getStatusCode().value();
			    System.out.println("error statusCode: "+statusCode); 
			}
			 
		     if(personEntity!=null) {		    	
				 System.out.println("Result - status ("+ personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());				    	 
			     System.out.println("Get all user service result: "+personEntity.toString()); 

			
		    		 //employees = personEntity.getBody();
				
			    // System.out.println("All users: "+employees); 
			     model.addAttribute("users", personEntity.getBody());
			     return "allUserInfo";

		     }    else {
		    	 System.out.println("null value"); 
		    	 return "error";
		     }


				
		}catch( Exception e){
			
			System.out.println("Exception occured: "+e);
			 return "error";
		}	        
	  
			
		}
	
	
	@RequestMapping(value = "deleteuser")
	public void deleteUser() throws Exception {
			
			System.out.println("At deleteuser controller");
			
			try{
			
			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://localhost:8080/SpringRestServer/service/user/delete/{id}";

			
			// UserForm response = null;
			 ResponseEntity<UserForm> personEntity = null;
			try {

			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.APPLICATION_JSON);
			        HttpEntity<String> entity = new HttpEntity<String>("", headers);
			        personEntity = restTemplate.exchange(uri, HttpMethod.DELETE, entity, UserForm.class, 15);
			       
			     
			} catch (HttpStatusCodeException exception) {
			    int statusCode = exception.getStatusCode().value();
			    System.out.println("error statusCode: "+statusCode); 
			}
			 
		     if(personEntity!=null) {
				  System.out.println("Result - status ("+ personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());
			        System.out.println("Delete service result: "+personEntity.toString()); 

		     }    else {
		    	 System.out.println("null value"); 

		     }


				
		}catch( Exception e){
			
			System.out.println("Exception occured: "+e);
		}	 
	        
	  
			
		}
	
	@RequestMapping(value = "updateuser")
	public void updateUser() throws Exception {
			
			System.out.println("At updateuser controller");
			
			try{
			
			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://localhost:8080/SpringRestServer/service/user/update/{id}";
           
			UserForm userForm = new UserForm("Dada-update-test", 1, 29, 10000000.00, "Test address");
			
			 ResponseEntity<UserForm> personEntity = null;
			try {

			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.APPLICATION_JSON);
			        HttpEntity<UserForm> entity = new HttpEntity<UserForm>(userForm, headers);
			        personEntity = restTemplate.exchange(uri, HttpMethod.PUT, entity, UserForm.class, 1); // going to update user with id 1
			       
			     
			} catch (HttpStatusCodeException exception) {
			    int statusCode = exception.getStatusCode().value();
			    System.out.println("error statusCode: "+statusCode); 
			}
			 
		     if(personEntity!=null) {
				  System.out.println("Result - status ("+ personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());
			        System.out.println("Update service result: "+personEntity.toString()); 

		     }    else {
		    	 System.out.println("null value"); 

		     }


				
		}catch( Exception e){
			
			System.out.println("Exception occured: "+e);
		}	 
	        
	  
			
		}
}
