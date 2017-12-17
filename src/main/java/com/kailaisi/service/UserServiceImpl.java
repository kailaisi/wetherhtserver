package com.kailaisi.service;

import com.kailaisi.mapper.UserMapper;
import com.kailaisi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findUsers() throws Exception {
        List<User> list = userMapper.selectByExample(null);
        return list;
    }

    @Override
    public User registor(User bean) {
        // TODO: 2017/12/18 需要判断user是否存在，如果存在，则返回失败信息，如果不存在，则注册，并返回注册后生成的注册信息
        Integer id = userMapper.insertSelective(bean);
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public User login(String phone, String pwd) {
        return null;
    }
}
