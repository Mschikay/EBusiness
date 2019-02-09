package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Card;
import com.app.model.Product;
import com.app.model.User;
import com.app.repository.CardRepository;
import com.app.repository.CartRepository;
import com.app.repository.OrderRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
//	{
//		"products": [{
//		  "productID": "1234123412341234",
//		  "count": 1
//		 },
//		 {
//		  "productID": "4321432143214321",
//		  "count": 1
//		 }
//		],
//		"email": "user1@gmail.com"
//		}
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private UserRepository userRepository;
	
	public void placeOrder(@RequestBody JSONObject jsonObject) {
		JSONArray jsonArray = (JSONArray)jsonObject.get("products");
		String cardNumber = (String) jsonObject.get("cardNumber");
		String email = (String) jsonObject.get("email");
		
		double total = 0;
		double[] newCount = new double[jsonArray.size()];
		Product[] newProducts = new Product[jsonArray.size()];
		for(int i=0; i<jsonArray.size(); i++){  
			JSONObject obj = (JSONObject) jsonArray.get(i);
			ObjectId productId = (ObjectId) obj.get("productId");
			Product p = productRepository.findById(productId).get();
			double price = p.getPrice();
			long count = (long) obj.get("count");
			if (count > p.getCount()) {
				//no enough quantity
			}else {
				p.setCount(p.getCount() - count);
				newProducts[i] = p;
			}
			total = total + price * count;
		}  
		
		Card c = cardRepository.findByEmail(email);
		if (c == null) {
			//return "no card";
		}
		if (c.getBalance() <= total) {
			// no enough money
		}else {
			c.setBalance(c.getBalance()-total);
		}
		
		for (int i = 0;i<newProducts.length;i++) {
			productRepository.save(newProducts[i]);
		}
		cardRepository.save(c);
		
		
		
	}
	
}
