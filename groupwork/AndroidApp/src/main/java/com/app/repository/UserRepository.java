package com.app.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.app.model.User;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    public List<User> findByUsername(@Param("username") String username);
    public User findByEmail(@Param("email") String email);
}
