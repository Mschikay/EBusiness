package com.app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.Order;

public interface OrderRepository extends MongoRepository<Order, ObjectId>{

}
