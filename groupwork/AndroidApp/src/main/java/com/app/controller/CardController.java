package com.app.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Card;
import com.app.model.User;
import com.app.repository.CardRepository;
import com.app.repository.UserRepository;

@RestController
@RequestMapping("/card")
public class CardController {

		@Autowired
		private CardRepository cardRepository;
		@Autowired
		private UserRepository userRepository;
		
		//add
		@RequestMapping(value="", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
	 	public String add(@RequestBody String json) {
			System.out.println(json);
			JSONObject jsonObject = new JSONObject(json);
			String email = jsonObject.getString("email");
			String cardNumber = jsonObject.getString("cardNumber");
			double balance = (double) jsonObject.getDouble("balance");
			
			if (balance < 0) {
				return "invalid balance";
			}
			User u = userRepository.findByEmail(email);
			if (u == null) {
				return "user not exist";
			}
			Card oldCard = cardRepository.findByEmailAndCardNumber(email, cardNumber);
			if (oldCard != null) {
				return "card already exists";
			}else {
				Card c = new Card(email, cardNumber, balance);
				cardRepository.save(c);
				return "add succeeded";	
			}
		}
}
