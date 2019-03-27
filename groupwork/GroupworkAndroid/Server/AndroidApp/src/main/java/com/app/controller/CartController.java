package com.app.controller;

import java.util.Date;

import org.json.JSONObject;
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

@RestController
@RequestMapping("/cart")
@ResponseStatus(value=HttpStatus.OK)
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	
	// add one kind of product to cart
	@RequestMapping(value="", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String add(@RequestBody String json) {
		//		private ObjectId _id;
		//		private ObjectId email;
		//		private ObjectId productName;
		//		private long count;
		//		private Date date;
		JSONObject jsonObject = new JSONObject(json);
		String email = jsonObject.getString("email");
		String productName = jsonObject.getString("productName");
		
		Cart oldCart = cartRepository.findCartByEmailAndProductName(email, productName);
		if (oldCart == null) {
			Cart cart = new Cart(email, productName, 1);
			cartRepository.save(cart);	
		}else {
			// already exist, add by 1
			oldCart.setCount(oldCart.getCount() + 1);
			cartRepository.save(oldCart);
		}
		return "add succeeded";
	}
	
	// update count
	@RequestMapping(value="", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String alterCount(@RequestBody String json) {
		//		private ObjectId _id;
		//		private ObjectId email;
		//		private ObjectId productName;
		//		private long count;
		//		private Date date;
		JSONObject jsonObject = new JSONObject(json);
		String email = jsonObject.getString("email");
		String productName = jsonObject.getString("productName");
		long count = (long) jsonObject.getLong("count");
		
		Cart oldCart = cartRepository.findCartByEmailAndProductName(email, productName);
		if (oldCart == null) {
			// product not exist in cart
			return "no this product in cart";
		}else {
			// exist, alter count
			oldCart.setCount(count);
			oldCart.setDate(new Date());
			cartRepository.save(oldCart);
			return "update succeeded";
		}
	}
	
	// delete
	@RequestMapping(value="", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteProduct(@RequestBody String json) {
		//		private ObjectId _id;
		//		private ObjectId email;
		//		private ObjectId productName;
		//		private long count;
		//		private Date date;
		JSONObject jsonObject = new JSONObject(json);
		String email = jsonObject.getString("email");
		String productName = jsonObject.getString("productName");
		
		Cart oldCart = cartRepository.findCartByEmailAndProductName(email, productName);
		if (oldCart == null) {
			// product not exist in cart
			return "no product in cart";
		}else {
			cartRepository.delete(oldCart);
			return "delete Succeeded";
		}
	}
	
	
}
