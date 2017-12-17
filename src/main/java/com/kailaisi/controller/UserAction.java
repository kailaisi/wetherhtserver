package com.kailaisi.controller;

import com.kailaisi.pojo.User;
import com.kailaisi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json; charset=utf-8"})
    public User register(@RequestBody User user) {
        return userService.registor(user);
    }

    @ResponseBody
    @RequestMapping(value = "/loginByPwd.do", method = RequestMethod.GET)
    public User loginByPWD(@RequestParam String phone, @RequestParam String pwd) {
        return userService.login(phone, pwd);
    }

    @ResponseBody
    @RequestMapping(value = "/findAll.do", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<User> findAll() throws Exception {
        return userService.findUsers();
    }
}
