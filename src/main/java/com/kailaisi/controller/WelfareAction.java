package com.kailaisi.controller;

import com.kailaisi.pojo.Welfare;
import com.kailaisi.service.WelfareServiceImpl;
import com.kailaisi.utils.GlableExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 福利信息
 */
@Controller
@RequestMapping("/welfare")
public class WelfareAction extends GlableExceptionHandler {
    @Resource
    private WelfareServiceImpl welfareService;

    @ResponseBody
    @RequestMapping(value = "/register.do", method = RequestMethod.POST, produces = {"application/json; charset=utf-8"})
    public Welfare register(@RequestBody Welfare welfare) {
        return welfareService.insert(welfare);
    }

    @ResponseBody
    @RequestMapping(value = "/findByPage.do",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public List<Welfare> page(@RequestParam int pageNum,@RequestParam int pageSize){
        return welfareService.findByPager(pageNum,pageSize);
    }
}
