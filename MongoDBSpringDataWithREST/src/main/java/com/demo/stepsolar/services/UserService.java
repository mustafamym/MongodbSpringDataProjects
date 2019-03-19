/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.stepsolar.services;

import com.demo.stepsolar.entity.User;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Mustafa
 */
public interface UserService {
        
    Iterable<User> findAll(Pageable pageable);
    
    User findById(String id);
    
    User save(User user);
    
    Boolean delete(String id);
}
