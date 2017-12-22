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

import javax.annotation.Resource;
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
        User users = userMapper.getUserByPhone(bean.getPhone());
        if (users == null) {
            Integer id = userMapper.register(bean);
            User user1 = userMapper.selectByPrimaryKey(id);
            saveOrUpdateToken(user1);
            return user1;
        } else {
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
            int i = tokenMapper.updateToken(tokenBean.getId(), token);
            Token token1 = tokenMapper.selectByPrimaryKey(tokenBean.getId());
            System.out.println(i);
        } else {
            tokenMapper.insert(user.getUsername(),token);
        }
    }
}
