package com.kailaisi.dao;

import com.kailaisi.bean.UserBean;

public interface UserDao {
    UserBean register(UserBean bean);
    UserBean login(String phone, String pwd);
    UserBean getUser(String phone);
}
