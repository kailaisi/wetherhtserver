package com.kailaisi.service;

import com.kailaisi.mapper.WelfareMapper;
import com.kailaisi.pojo.Welfare;
import com.kailaisi.utils.JUnit4ClassRunner;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/*.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class WelfareServiceImplTest extends TestCase {
    @Resource
    private WelfareServiceImpl welfareService;
    @Test
    public void testInsert() throws Exception {
        Welfare bean=new Welfare();
        bean.setDescription("测试");
        bean.setName("asepsd");
        bean.setCreatetime(new Date());
        welfareService.insert(bean);
    }
    @Test
    public void testFindByPage() throws Exception{
        List<Welfare> byPager = welfareService.findByPager(10, 10);
        assertEquals(byPager.size(),10);
    }
}