package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    @Test
    void savesAndFindsUser() {
        UserService userService = new UserServiceImpl();
        User user = new User(1L, "张三");

        userService.save(user);

        assertEquals(user, userService.findById(1L));
        assertEquals(1, userService.findAll().size());
    }
}
