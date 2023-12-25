package com.project.ecommerce.service;

import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.exception.InternalServerErrorException;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity addUser(UserEntity user) {
        try {
            UserEntity userSaved = userRepository.save(user);
            return userSaved;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public UserEntity getUserByUserId(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        return user.orElseThrow(null);
    }

    public UserEntity getUserById(Long userId) {
        try {
            UserEntity user = getUserByUserId(userId);
            if(user!=null){
                return user;
            }
            throw new ResourceNotFoundException("User not found");
        } catch (ResourceNotFoundException e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }
}
