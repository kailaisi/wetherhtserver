package com.kailaisi.service;

import com.kailaisi.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findUsers() throws Exception;

    User register(User bean);

    User login(String username, String pwd);
}