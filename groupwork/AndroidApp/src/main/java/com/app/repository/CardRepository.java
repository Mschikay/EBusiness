package com.app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.Card;

public interface CardRepository extends MongoRepository<Card, ObjectId>{
	public Card findByEmail(String email);
	public Card findByEmailAndCardNumber(String email, String cardNumber);
}
