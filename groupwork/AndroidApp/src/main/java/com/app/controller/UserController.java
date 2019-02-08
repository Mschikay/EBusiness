package com.app.controller;

import java.util.List;

import com.app.Start;
import com.app.model.User;
import com.app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;



@RestController
@RequestMapping("/user")
public class UserController {
	
    @Autowired
	private UserRepository repository;
	
    // login
 	@RequestMapping(value="/login", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
 	@ResponseStatus(value=HttpStatus.OK)
 	@ResponseBody
 	public String login(@RequestBody JSONObject jsonObject) {
 		String email = (String) jsonObject.get("email");
 		String password = (String) jsonObject.get("password");
 		
 		User user = repository.findByEmail(email);
 		if (user == null) {
 			return "not found, failed";
 		}
 		
 		String p = user.getPassword();
 		if (p.equals(password)) {
 			return user.getUsername();
 		}
 		return "wrong password, failed!";
 	}
 	
	// register
	@RequestMapping(value="/register", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public String create(@RequestBody JSONObject jsonObject) {
		String username = (String) jsonObject.get("username");
		String email = (String) jsonObject.get("email");
		String password = (String) jsonObject.get("password");
		
		User oldUser = repository.findByEmail(email);
		if (oldUser != null) {
			return "failed, duplicated email";
		}
		
		User user = new User(username, email, password);
		repository.save(user);
		return "register succeeded";
	}
	

	//read by default it is GET
	@RequestMapping(value="/{email}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public User read(@RequestParam String email) {
		return repository.findByEmail(email);
	}
	
	//update
	@RequestMapping(value="", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public String update(@RequestBody JSONObject jsonObject) {
		String username = (String) jsonObject.get("username");
		String email = (String) jsonObject.get("email");
		String password = (String) jsonObject.get("password");
		
		if (email == null) {
			return "invalid email";
		}
		User oldUser = repository.findByEmail(email);
		if (oldUser == null) {
			return "user not exist";
		}
		
		if (username == null || username == "") {
			oldUser.setUsername(oldUser.getUsername());
		}else {
			oldUser.setUsername(username);
		}
		if (password == null || password == "") {
			oldUser.setPassword(oldUser.getPassword());
		}else {
			oldUser.setPassword(password);
		}
		repository.save(oldUser);
		return "update succeeded";
	}
	
	//delete e.g. http://localhost:8080/user?email=user9@gmail.com
	@RequestMapping(value="", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public String delete(@RequestParam("email") String email) {
		System.out.println(email);
		User user = repository.findByEmail(email);
		if (user == null) {
			return "user not exist";
		}
		
		ObjectId id = user.getId();
		repository.deleteById(id);
		return "delete succeeded";
	}
}
