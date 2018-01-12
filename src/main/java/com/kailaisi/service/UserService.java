package com.kailaisi.service;

import com.kailaisi.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> findUsers() throws Exception;

    /**
     * 注册
     *
     * @param bean
     * @return
     */
    User register(User bean);

    /**
     * 用户登录
     *
     * @param username
     * @param pwd
     * @return
     */
    User login(String username, String pwd);

    /**
     * 上传头像
     *
     * @return
     * @param username
     * @param file
     */
    String uploadHead(String username, MultipartFile file);
}