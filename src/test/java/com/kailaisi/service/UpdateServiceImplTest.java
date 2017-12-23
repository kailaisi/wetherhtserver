package com.kailaisi.service;

import com.kailaisi.utils.JUnit4ClassRunner;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/*.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UpdateServiceImplTest extends TestCase {
    @Test
    public void testCheckUpdate() throws Exception {

    }

}