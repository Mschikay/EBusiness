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

import com.app.model.Product;
import com.app.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	//add
	@RequestMapping(value="", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
 	@ResponseStatus(value=HttpStatus.OK)
 	@ResponseBody
 	public String add(@RequestBody JSONObject jsonObject) {
		String name = (String) jsonObject.get("name");
		double price = (double) jsonObject.get("price");
		long count = (long) jsonObject.get("count");
		Product p = new Product(name, price, count);
		productRepository.save(p);
		return "add succeeded";
	}
	
	//update
	@RequestMapping(value="", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public String update(@RequestBody JSONObject jsonObject) {
		String name = (String) jsonObject.get("name");
		double price = (double) jsonObject.get("price");
		long count = (long) jsonObject.get("count");
			
			if (name == null) {
				return "invalid email";
			}
			Product oldProduct = productRepository.findByName(name);
			if (oldProduct == null) {
				return "user not exist";
			}
			
			if (price < 0 ) {
				oldProduct.setPrice(oldProduct.getPrice());
			}else {
				oldProduct.setPrice(price);
			}
			if (count < 0) {
				oldProduct.setCount(oldProduct.getCount());
			}else {
				oldProduct.setCount(count);
			}
			productRepository.save(oldProduct);
			return "update succeeded";
		}
		
		//delete e.g. http://localhost:8080/product?name=Friendly milk
		@RequestMapping(value="", method=RequestMethod.DELETE)
		@ResponseStatus(value=HttpStatus.OK)
		@ResponseBody
		public String delete(@RequestParam("name") String name) {
			Product p = productRepository.findByName(name);
			if (p == null) {
				return "product not exist";
			}
			
			ObjectId id = p.getId();
			productRepository.deleteById(id);
			return "delete succeeded";
		}
}
