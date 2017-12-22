package com.kailaisi.service;

import com.alibaba.fastjson.JSON;
import com.kailaisi.pojo.User;
import com.kailaisi.utils.JUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/*.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Test
    public void testFindUsers() throws Exception {
        List<User> users = userService.findUsers();
        System.out.println(users.size());
    }

    @Test
    public void testRegistor() throws Exception {
        User user = new User();
        user.setPhone("15122112703");
        user.setAddress("天津市南开区凯德大厦c座4楼409");
        user.setUsername("wulei");
        user.setAge(43);
        user.setPassword("wu03102896528");
        User user1 = userService.register(user);
        logger.info(JSON.toJSONString(user1));
    }

    @Test
    public void testLogin() throws Exception {
        User user = userService.login("kailaisi", "wu03102896528");
        logger.info(JSON.toJSONString(user));
    }

}