package com.kailaisi.service;

import com.kailaisi.mapper.WelfareMapper;
import com.kailaisi.pojo.Welfare;
import com.kailaisi.utils.ApiException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("welfareService")
public class WelfareServiceImpl {
    @Resource
    private WelfareMapper welfareMapper;

    public Welfare insert(Welfare bean) {
        Welfare welfare = welfareMapper.findByName(bean.getName());
        if (welfare == null) {
            welfareMapper.insert(bean);
            return bean;
        } else {
            throw new ApiException(1001, "该文件已存在");
        }
    }

    public List<Welfare> findByPager(int pageNum, int pageSize) {
        int start = pageSize * pageNum;
       return welfareMapper.findByPager(start, pageSize);
    }
}
