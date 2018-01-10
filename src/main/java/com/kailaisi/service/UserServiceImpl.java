package com.kailaisi.service;

import com.kailaisi.mapper.TokenMapper;
import com.kailaisi.mapper.UserMapper;
import com.kailaisi.pojo.Token;
import com.kailaisi.pojo.User;
import com.kailaisi.utils.ApiException;
import com.kailaisi.utils.CodeEnums;
import com.kailaisi.utils.MD5;
import com.kailaisi.utils.PhoneFormatCheckUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private TokenMapper tokenMapper;

    public List<User> findUsers() throws Exception {
        List<User> list = userMapper.findAll();
        return list;
    }

    @Override
    public User register(User bean) {
        if (!PhoneFormatCheckUtils.isChinaPhoneLegal(bean.getPhone())) {
            throw new ApiException(CodeEnums.NAME_NOT_ALLOWED);
        }
        List<User> users = userMapper.getUserByPhoneAndName(bean.getPhone(), bean.getUsername());
        if (users == null || users.size() == 0) {
            //mapper会把生成的主键直接赋值给bean类。
            userMapper.register(bean);
            saveOrUpdateToken(bean);
            return bean;
        } else {
            System.out.println(bean.getPhone() + "用户已经存在");
            throw new ApiException(CodeEnums.USER_EXIST);
        }
    }

    @Override
    public User login(String username, String pwd) {
        User user = userMapper.findByNameAndPwd(username, pwd);
        if (user == null) {
            throw new ApiException(CodeEnums.USER_NOT_EXIST);
        } else {
            saveOrUpdateToken(user);
            return user;
        }
    }

    @Override
    public String uploadHead(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException(1, "文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        if(!fileName.toLowerCase().endsWith(".png")&& !fileName.toLowerCase().endsWith("jpg")){
            throw new ApiException(1, "只支持png和jpg格式文件");
        }
        long size = file.getSize();
        if(size>1024*1024){
            throw new ApiException(1, "头像大小不能超过1M");
        }
        else {
            try {
                File base = new File("D:\\server\\header");
                String filepath = new File(base, fileName).getAbsolutePath();
                file.transferTo(new File(filepath));
                return "/header/"+fileName;
            } catch (IOException e) {
                e.printStackTrace();
                throw new ApiException(1, "文件上传失败");
            }
        }
    }

    /**
     * 更新token值
     *
     * @param user
     */
    private void saveOrUpdateToken(User user) {
        String token = null;
        try {
            token = MD5.encryptMD5(String.valueOf(System.currentTimeMillis() + "appservice.02154778ke783dad34"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setToken(token);
        Token tokenBean = tokenMapper.isTokenAvailable(user.getUsername());
        if (tokenBean != null) {
            tokenMapper.updateToken(tokenBean.getId(), token);
        } else {
            tokenMapper.insert(user.getUsername(), token);
        }
    }

}
