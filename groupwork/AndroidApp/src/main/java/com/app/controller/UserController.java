package com.app.controller;

import java.util.List;

import com.app.Start;
import com.app.model.User;
import com.app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	// register
	@RequestMapping(value="/register", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String create(@RequestBody User user) {
		repository.save(user);
		return "register succeeded";
	}
	
	// login
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String test(@RequestBody String email, String password) {
		User user = repository.findByEmail(email);
		String p = user.getPassword();
		if (p == password) {
			return "login succeeded";
		}
		return "failed!";
	}

	//read by default it is GET
	@RequestMapping(value="/{email}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody User read(@RequestParam String email) {
		return repository.findByEmail(email);
	}
	
	//update
	@RequestMapping(value="", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String update(User user) {
		repository.save(user);
		return "update succeeded";
	}
	
	//delete
	@RequestMapping(value="/{email}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String delete(String email) {
		User user = repository.findByEmail(email);
		repository.deleteById(user.getId());
		return "delete succeeded";
	}
	
	public void test() {
		User u = new User();
		u.setEmail("user2@gmail.com");
		u.setId("02");
		u.setPassword("123");
		u.setUsername("user2");
		repository.save(u);
		List<User> user = repository.findAll();
		System.out.println(user.size());
		User x = repository.findByEmail("user1@gmail.com");
		System.out.println(x.getId());
	}
	
	public static void main(String[] args) {
		UserController u = new UserController();
		u.test();
	}
}
