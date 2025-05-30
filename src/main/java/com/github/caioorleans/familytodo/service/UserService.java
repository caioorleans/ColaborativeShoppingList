package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.model.User;

public interface UserService {

    User createUser(User user);
    User findUserByEmail(String email);
}
