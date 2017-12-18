package com.kailaisi.service;

import com.kailaisi.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findUsers() throws Exception;

    User registor(User bean);

    User login(String phone, String pwd);
}