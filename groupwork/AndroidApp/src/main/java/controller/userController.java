package controller;

import model.User;
import repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {
	
	@Autowired
	UserRepository userRepository;
	
	// create
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody User user) {
		userRepository.save(user);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String test() {
		return "this is a test";
	}

	//read by default it is GET
	@RequestMapping(method=RequestMethod.GET, value="/{email}")
	public User read(@RequestParam String email) {
		return userRepository.findByEmail(email);
	}
	
	//update
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void update(User user) {
		userRepository.save(user);
	}
	
	//delete
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void delete(String id) {
		userRepository.deleteById(id);
	}

}
