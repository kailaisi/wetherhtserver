package com.kailaisi.mapper;

import com.kailaisi.pojo.Token;
import org.apache.ibatis.annotations.Param;

public interface TokenMapper {
    Token selectByPrimaryKey(Integer id);

    Token isTokenAvailable(String username);

    void updateOrInsert(Token tokenBean);

    int updateToken(@Param("id") Integer id, @Param("token") String token);

    void insert(@Param("username") String username, @Param("token") String token);
}