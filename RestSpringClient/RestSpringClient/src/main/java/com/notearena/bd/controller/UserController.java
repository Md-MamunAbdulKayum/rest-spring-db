package com.notearena.bd.controller;

import java.util.ArrayList;
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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.notearena.bd.model.UserForm;

@Controller
public class UserController {
	private List<UserForm> employees;

	@RequestMapping(value = "/")
	public String getHomepage(ModelMap model) throws Exception {

		System.out.println("At getuserlist controller");

		try {

			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://localhost:8080/SpringRestServer/service/user/";

			ResponseEntity<List<UserForm>> personEntity = null;
			try {

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>("", headers);
				System.out.println("Getting list");

				personEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<UserForm>>() {
						});
				System.out.println("Address:" + personEntity.getBody().get(0).getAddress());

			} catch (HttpStatusCodeException exception) {
				int statusCode = exception.getStatusCode().value();
				System.out.println("error statusCode: " + statusCode);
				return "error";
			}

			if (personEntity != null) {
				System.out.println(
						"Result - status (" + personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());
				System.out.println("Get all user service result: " + personEntity.toString());
				model.addAttribute("users", personEntity.getBody());
			} else {
				System.out.println("No user found");
				model.addAttribute("users", null);
			}

		} catch (Exception e) {

			System.out.println("Exception occured: " + e);
			return "error";
		}
		return "index";
	}



	@RequestMapping(value = "getuser/{id}")
	public String getUser(@PathVariable("id") Long userId, ModelMap model) throws Exception {

		System.out.println("At getuser controller");

		try {

			// UserForm response = null;
			ResponseEntity<UserForm> personEntity = getUser(userId);

			if (personEntity != null) {
				System.out.println("Get particular user service result: " + personEntity.toString());
				System.out.println("User name: " + personEntity.getBody().getUserName());
				model.addAttribute("user", personEntity.getBody());
				model.addAttribute("name", personEntity.getBody().getUserName());				
			} else {
				System.out.println("null value");
			}

		} catch (Exception e) {

			System.out.println("Exception occured: " + e);
			return "error";
		}
		return "userInfo";
	}

	@RequestMapping(value = "getuserlist")
	public String getUsersList(ModelMap model) throws Exception {

		System.out.println("At getuserlist controller");

		try {

			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://localhost:8080/SpringRestServer/service/user/";

			ResponseEntity<List<UserForm>> personEntity = null;
			try {

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>("", headers);
				System.out.println("Getting list");
				
				personEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<UserForm>>() {
						});
			} catch (HttpStatusCodeException exception) {
				int statusCode = exception.getStatusCode().value();
				System.out.println("error statusCode: " + statusCode);
				return "error";
			}

			if (personEntity != null) {
				System.out.println(
						"Result - status (" + personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());
				System.out.println("Get all user service result: " + personEntity.toString());
				model.addAttribute("users", personEntity.getBody());

			} else {
				System.out.println("No user found");
				model.addAttribute("users", null);
			}

		} catch (Exception e) {

			System.out.println("Exception occured: " + e);
			return "error";
		}
		return "allUserInfo";
	}

	@RequestMapping(value = "createuser", method = RequestMethod.GET)
	public String createUser(Model model) {
		System.out.println("Check add user");
		model.addAttribute("userObject", new UserForm());
		return "addUser";
	}

	
	
	@RequestMapping(value = "createuser", method = RequestMethod.POST)
	public String createUser(@RequestParam(required = true) String userName, @RequestParam(required = true) int age,
			@RequestParam(required = true) double salary, @RequestParam(required = true) String address,
			@ModelAttribute("userObject") UserForm userObject, Model model) throws Exception {

		System.out.println("At createuser controller");

		try {

			RestTemplate restTemplate = new RestTemplate();
			// convert the response message to a standard form
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));         
			messageConverters.add(converter);  
			restTemplate.setMessageConverters(messageConverters);  

			String uri = "http://localhost:8080/SpringRestServer/service/user/create";

			UserForm userForm = new UserForm(userName, age, salary, address);

			ResponseEntity<UserForm> personEntity = null;
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				// Data attached to the request.
				HttpEntity<UserForm> entity = new HttpEntity<UserForm>(userForm, headers);
				personEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, UserForm.class);
				System.out.println("Address:" + personEntity.getBody().getAddress());
			} catch (HttpStatusCodeException exception) {
			    int statusCode = exception.getStatusCode().value();
			    System.out.println("error statusCode: "+statusCode);  
				return "redirect:/error";
			}

			if (personEntity != null) {
				System.out.println(
						"Result - status (" + personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());
				System.out.println("Create user service result: " + personEntity.getBody().toString());

			}

		} catch (Exception e) {

			System.out.println("Exception occured:------ " + e);
			return "redirect:/error";
		}
		return "redirect:/getuserlist";

	}
	
	@RequestMapping(value = "deleteuser/{id}")
	public String deleteUser(@PathVariable("id") Long userId) throws Exception {

		System.out.println("At deleteuser controller");

		try {

			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://localhost:8080/SpringRestServer/service/user/delete/"+userId;

			// UserForm response = null;
			ResponseEntity<UserForm> personEntity = null;
			try {

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>("", headers);
				personEntity = restTemplate.exchange(uri, HttpMethod.DELETE, entity, UserForm.class, 15);

			} catch (HttpStatusCodeException exception) {
				int statusCode = exception.getStatusCode().value();
				System.out.println("error statusCode: " + statusCode);
				return "redirect:/error";
			}

			if (personEntity != null) {
				System.out.println(
						"Result - status (" + personEntity.getStatusCode() + ") has body: " + personEntity.hasBody());
				System.out.println("Delete service result: " + personEntity.toString());

			} else {
				System.out.println("null value");
			}

		} catch (Exception e) {

			System.out.println("Exception occured: " + e);
			return "redirect:/error";
		}
		return "redirect:/getuserlist";
	}


	
	@RequestMapping(value = "updateuser/{id}", method = RequestMethod.GET)
	public String updateUser(@PathVariable("id") Long userId, Model model) throws Exception {

		System.out.println("At updateuser controller, user id:"+userId);

		try {
			
			ResponseEntity<UserForm> userEntity = getUser(userId);
			UserForm userForm = new UserForm(userEntity.getBody().getUserName(), userEntity.getBody().getUserId(), userEntity.getBody().getAge(), userEntity.getBody().getSalary(), userEntity.getBody().getAddress());
			model.addAttribute("userObject", userForm);
	
		} catch (Exception e) {
			System.out.println("Exception occured: " + e);
			return "error";
		}
		return "editUser";
	}
	
	
	@RequestMapping(value = "updateuser", method = RequestMethod.POST)
	public String updateUser(@RequestParam(required = true) String userName, @RequestParam(required = true) int age,
			@RequestParam(required = true) double salary, @RequestParam(required = true) String address,
			@ModelAttribute("userObject") UserForm userObject, Model model) throws Exception {

		System.out.println("At updateuser controller, user id:"+userObject.getUserId());

		try {
			

			RestTemplate restTemplate = new RestTemplate();
			// convert the response message to a standard form
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));         
			messageConverters.add(converter);  
			restTemplate.setMessageConverters(messageConverters);  
			

			String uri = "http://localhost:8080/SpringRestServer/service/user/update/";

			UserForm userForm = new UserForm(userObject.getUserName(), userObject.getUserId(), userObject.getAge(), userObject.getSalary(), userObject.getAddress());

			ResponseEntity<UserForm> userEntityUpdate = null;
			try {

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<UserForm> entity = new HttpEntity<UserForm>(userForm, headers);
				userEntityUpdate = restTemplate.exchange(uri, HttpMethod.PUT, entity, UserForm.class, 1); // going to update
																										// user with id																										
			} catch (HttpStatusCodeException exception) {
				int statusCode = exception.getStatusCode().value();
				System.out.println("error statusCode: " + statusCode);
				return "redirect:/error";
			}

			if (userEntityUpdate != null) {
				System.out.println(
						"Result - status (" + userEntityUpdate.getStatusCode() + ") has body: " + userEntityUpdate.hasBody());
				System.out.println("Update service result: " + userEntityUpdate.toString());

			} else {
				System.out.println("null value");
			}

		} catch (Exception e) {
			System.out.println("Exception occured: " + e);
			return "redirect:/error";
		}
		return "redirect:/getuserlist";
	}

	
	@RequestMapping(value = "error")
	public String getErrorPage() throws Exception {
		return "error";
	}
	
	
	private ResponseEntity<UserForm> getUser(Long userId ) {
		RestTemplate restTemplate = new RestTemplate();

		String uri = "http://localhost:8080/SpringRestServer/service/user/"+userId;
		ResponseEntity<UserForm> personEntity = null;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>("", headers);
			personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, UserForm.class, 1);
			System.out.println("Address:" + personEntity.getBody().getAddress());

		} catch (HttpStatusCodeException exception) {
			int statusCode = exception.getStatusCode().value();
			System.out.println("error statusCode: " + statusCode);
			return null;
		}
		
		
		return personEntity;
	}
}
