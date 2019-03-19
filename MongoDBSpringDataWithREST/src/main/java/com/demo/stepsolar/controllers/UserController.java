package com.demo.stepsolar.controllers;

import com.demo.stepsolar.constant.ResponseTagName;
import com.demo.stepsolar.entity.User;
import com.demo.stepsolar.services.UserService;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping(path = "api/users", produces = {"application/json"})
public class UserController {


    @Autowired
    private UserService userService;

    private LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<Object, Object>();
    private LinkedHashMap<String, Object> serviceResponse = new LinkedHashMap<String, Object>();
    
    @PostMapping
    ResponseEntity<?> post(@RequestBody User user) {

       User o = userService.save(user);
        serviceResponse.put(ResponseTagName.CREATED, o);
        apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable("id") String id) {
        User o = userService.findById(id);
        if (o != null) {
      serviceResponse.put(ResponseTagName.RETRIEVED, 0);
        apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/list")
    ResponseEntity<?> list(Pageable pageable) {
        List<User> users = (List<User>) userService.findAll(pageable);
         serviceResponse.put(ResponseTagName.LIST, users);
        apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    
    }


    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody User user) {

    
       User o = userService.save(user);
        serviceResponse.put(ResponseTagName.RETRIEVED, 0);
        apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id) {
 boolean isDeleted = userService.delete(id);
            if (isDeleted) {
               
                apiResponse.put(ResponseTagName.STATUS, Boolean.TRUE);
                apiResponse.put(ResponseTagName.MESSAGE, "Your statistics has been deleted successfully.");
                return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
            } else {
 
                return new ResponseEntity<Object>("error occured while delete", new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
    }

}
