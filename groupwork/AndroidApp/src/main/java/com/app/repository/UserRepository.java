package com.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.User;


public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findByUsername(String username);
    public User findByEmail(String email);
}
