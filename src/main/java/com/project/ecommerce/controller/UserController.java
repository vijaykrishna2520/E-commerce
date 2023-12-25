package com.project.ecommerce.controller;

import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userservice;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserEntity user){
        userservice.addUser(user);
        return new ResponseEntity<>("New user created successfully", HttpStatus.CREATED);
    }

}
