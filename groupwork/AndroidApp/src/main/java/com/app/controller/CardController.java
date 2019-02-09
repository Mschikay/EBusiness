package com.app.controller;

import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Card;
import com.app.model.Product;
import com.app.model.User;
import com.app.repository.CardRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;

public class CardController {
	@RestController
	@RequestMapping("/card")
	public class ProductController {
		@Autowired
		private CardRepository cardRepository;
		@Autowired
		private UserRepository userRepository;
		
		//add
		@RequestMapping(value="", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	 	@ResponseStatus(value=HttpStatus.OK)
	 	@ResponseBody
	 	public String add(@RequestBody JSONObject jsonObject) {
			String email = (String) jsonObject.get("email");
			String cardNumber = (String) jsonObject.get("cardNumber");
			double balance = (double) jsonObject.get("balance");
			
			User u = userRepository.findByEmail(email);
			if (u == null) {
				return "user not exist";
			}
			Card c = new Card(email, cardNumber, balance);
			cardRepository.save(c);
			return "add succeeded";
		}
		
	}

}
