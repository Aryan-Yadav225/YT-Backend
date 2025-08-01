package com.aryan.yadav.YT_Backend.Repository;

import com.aryan.yadav.YT_Backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findBySub(String sub);
}