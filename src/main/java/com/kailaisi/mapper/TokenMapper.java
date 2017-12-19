package com.kailaisi.mapper;

import com.kailaisi.pojo.TokenExample;
import com.kailaisi.pojo.TokenKey;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TokenMapper {
    int countByExample(TokenExample example);

    int deleteByExample(TokenExample example);

    int deleteByPrimaryKey(TokenKey key);

    int insert(TokenKey record);

    int insertSelective(TokenKey record);

    List<TokenKey> selectByExample(TokenExample example);

    int updateByExampleSelective(@Param("record") TokenKey record, @Param("example") TokenExample example);

    int updateByExample(@Param("record") TokenKey record, @Param("example") TokenExample example);


    TokenKey isTokenAvailable(String username);

    void updateOrAdd(@Param("record") TokenKey record);
}