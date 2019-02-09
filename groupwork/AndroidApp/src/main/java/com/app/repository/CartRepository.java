package com.app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.Cart;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {

}
