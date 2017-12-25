package com.kailaisi.controller;

import com.kailaisi.pojo.Welfare;
import com.kailaisi.service.WelfareServiceImpl;
import com.kailaisi.utils.GlableExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
}
