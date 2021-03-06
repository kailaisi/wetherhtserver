package com.kailaisi.controller;

import com.kailaisi.pojo.User;
import com.kailaisi.service.UserService;
import com.kailaisi.utils.GlableExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserAction extends GlableExceptionHandler {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register.do", method = RequestMethod.POST, produces = {"application/json; charset=utf-8"})
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public User loginByPWD(@RequestParam String username, @RequestParam String pwd) {
        return userService.login(username, pwd);
    }

    @ResponseBody
    @RequestMapping(value = "/findAll.do", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public List<User> findAll() throws Exception {
        return userService.findUsers();
    }

    @ResponseBody
    @RequestMapping(value = "/upload.do",method = RequestMethod.POST)
    public String uploadHeadImg(@RequestParam String username,@RequestParam("file") MultipartFile file) {
        return userService.uploadHead(username,file);
    }
}
