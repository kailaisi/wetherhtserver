package com.kailaisi.mapper;

import com.kailaisi.pojo.Welfare;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface WelfareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Welfare record);

    int insertSelective(Welfare record);

    Welfare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Welfare record);

    int updateByPrimaryKey(Welfare record);

    Welfare findByName(String name);

    List<Welfare> findByPager(@Param("start") int start, @Param("size") int size);
}