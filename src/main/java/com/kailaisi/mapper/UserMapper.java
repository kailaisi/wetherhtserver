package com.kailaisi.mapper;

import com.kailaisi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectByPrimaryKey(Integer id);

    User findByNameAndPwd(@Param("username") String username, @Param("pwd") String pwd);


    User getUserByPhone(@Param("phone") String phone);

    List<User> findAll();

    Integer register(User bean);
}