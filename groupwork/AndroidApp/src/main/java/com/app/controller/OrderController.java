package com.app.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Card;
import com.app.model.Order;
import com.app.model.Product;
import com.app.repository.CardRepository;
import com.app.repository.OrderRepository;
import com.app.repository.ProductRepository;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@RequestMapping(value="", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String placeOrder(@RequestBody String json) {
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("products");
		String email = jsonObject.getString("email");
		
		double total = 0;
		Product[] newProducts = new Product[jsonArray.length()];
		long[] countToBuy = new long[jsonArray.length()]; 
		for(int i=0; i<jsonArray.length(); i++){  
			JSONObject obj = (JSONObject) jsonArray.get(i);
			String productName = obj.getString("productName");
			long count = (long) obj.getLong("count");
			System.out.println(productName);
			System.out.println(count);

			Product p = productRepository.findByName(productName);
			if (p == null) {
				return "no such product";
			}
			if (count > p.getCount()) {
				return "storage not enough";
			}else {
				p.setCount(p.getCount() - count);
				newProducts[i] = p;
				countToBuy[i] = count;
			}
			total = total + p.getPrice() * count;
		}  
		
		// alter card info
		Card c = cardRepository.findByEmail(email);
		if (c == null) {
			return "user has no card";
		}
		if (c.getBalance() < total) {
			return "no enough money";
		}else {
			c.setBalance(c.getBalance()-total);
		}
		
		// save card and products
		for (int i = 0;i<newProducts.length;i++) {
			Product p = newProducts[i];
			productRepository.save(newProducts[i]);
			Order order = new Order(email, c.getCardNumber(), p.getName(), countToBuy[i]);
			orderRepository.save(order);
		}
		cardRepository.save(c);
		return "add succeeded";
	}
	
}
