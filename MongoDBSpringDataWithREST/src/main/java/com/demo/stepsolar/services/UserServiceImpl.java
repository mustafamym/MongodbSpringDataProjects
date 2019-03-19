/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.stepsolar.services;

import com.demo.stepsolar.entity.User;
import com.demo.stepsolar.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mustafa
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public User findById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean delete(String id) {
       userRepository.delete(id);
        return !userRepository.exists(id);
    }

}
