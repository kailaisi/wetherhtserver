package com.kailaisi.service;

import com.kailaisi.mapper.UserMapper;
import com.kailaisi.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserServiceImplTest{
    @Test
    public void testFindUsers() throws Exception {
        String resource = "config/spring/spring-dao.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> lists = userMapper.selectByExample(null);
        for(User user:lists) {
            System.out.println(user.toString());
        }
    }

    public void testRegistor() throws Exception {
    }

    public void testLogin() throws Exception {
    }

}