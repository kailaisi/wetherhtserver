package com.kailaisi.mapper;

import com.kailaisi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectByPrimaryKey(Integer id);

    User findByNameAndPwd(@Param("username") String username, @Param("pwd") String pwd);

    List<User> findAll();

    Integer register(User bean);

    List<User> getUserByPhoneAndName(@Param("phone") String phone, @Param("username") String username);

    void updateHeader(@Param("username") String username, @Param("head") String fileName);
}