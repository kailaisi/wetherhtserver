package com.kailaisi.mapper;

import com.kailaisi.pojo.Update;
import org.apache.ibatis.annotations.Param;

public interface UpdateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Update record);

    int insertSelective(Update record);

    Update selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Update record);

    int updateByPrimaryKey(Update record);

    Update getUpdateInfo(@Param("md5Value") String md5Value,@Param("versioncode") int versioncode,@Param("channelid") String channelid);
}