package com.app.controller;

import java.util.Date;

import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Cart;
import com.app.repository.CartRepository;
import com.app.repository.ProductRepository;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartRepository cartRepository;
	
	// add one kind of product
	@RequestMapping(value="/addbyid", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
 	@ResponseStatus(value=HttpStatus.OK)
 	@ResponseBody
	public void add(@RequestBody JSONObject jsonObject) {
//		private ObjectId _id;
//		private ObjectId userId;
//		private ObjectId productId;
//		private long count;
//		private Date date;
		ObjectId userId = (ObjectId) jsonObject.get("userId");
		ObjectId productId = (ObjectId) jsonObject.get("productId");
		long count = (long) jsonObject.get("count");
		
		// productId and userId already exist, no save
		Cart cart = new Cart(userId, productId, count);
		cartRepository.save(cart);

		// return json response
	}
}
