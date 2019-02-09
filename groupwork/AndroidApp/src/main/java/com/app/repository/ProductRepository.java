package com.app.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.app.model.Product;
import com.app.model.User;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
	public Optional<Product> findById(@Param("_id")ObjectId id);

	public Product findByName(String name);
}
