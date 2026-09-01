package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final Map<Long, User> users = new LinkedHashMap<Long, User>();

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public User findById(Long id) {
        return users.get(id);
    }

    @Override
    public User save(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("用户及用户 ID 不能为空");
        }
        users.put(user.getId(), user);
        return user;
    }
}
