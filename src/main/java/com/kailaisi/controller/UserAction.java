package com.kailaisi.controller;

import com.kailaisi.bean.RestFulBean;
import com.kailaisi.bean.UserBean;
import com.kailaisi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

public class UserAction {
    @Autowired
    private UserService userService=new UserService();

    @ResponseBody
    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public RestFulBean<UserBean> register(@RequestBody UserBean bean) {
        System.out.println("phone:" + bean.getPhone());
        return userService.registorServer(bean);
    }

    @ResponseBody
    @RequestMapping(value = "/loginByPwd.do", method = RequestMethod.GET)
    public RestFulBean<UserBean> loginByPWD(@RequestParam String phone, @RequestParam String pwd) {
        return userService.login(phone, pwd);
    }
}
