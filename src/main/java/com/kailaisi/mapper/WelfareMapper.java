package com.kailaisi.mapper;

import com.kailaisi.pojo.Welfare;

public interface WelfareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Welfare record);

    int insertSelective(Welfare record);

    Welfare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Welfare record);

    int updateByPrimaryKey(Welfare record);

    Welfare findByName(String name);
}