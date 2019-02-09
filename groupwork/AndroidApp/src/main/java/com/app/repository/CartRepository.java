package com.app.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.Cart;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {
	Cart findCartByEmailAndProductName(String email, String productName);
}
