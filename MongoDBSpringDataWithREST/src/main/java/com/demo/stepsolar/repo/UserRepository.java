package com.demo.stepsolar.repo;

import com.demo.stepsolar.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
       public List<User> findByEmail(String email);

}
