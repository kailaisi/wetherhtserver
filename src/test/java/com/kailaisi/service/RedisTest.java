package com.kailaisi.service;

import com.kailaisi.utils.JUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/*.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class RedisTest {
    @Test
    public void testRedis() {
        JedisPoolConfig conf = new JedisPoolConfig();
        JedisPool pool = new JedisPool(conf, "127.0.0.1", 6379, 5000, "wu03102896528");
        Jedis jedis = pool.getResource();
        jedis.set("foo","bar");
        String foo = jedis.get("foo");
        System.out.println(foo);
    }
}
