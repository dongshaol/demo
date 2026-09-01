package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> listUsers() {
        return userService.findAll();
    }

    public User getUser(Long id) {
        return userService.findById(id);
    }

    public User createUser(User user) {
        return userService.save(user);
    }
}
