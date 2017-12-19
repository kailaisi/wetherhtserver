package com.kailaisi.service;

import com.kailaisi.mapper.TokenMapper;
import com.kailaisi.mapper.UserMapper;
import com.kailaisi.pojo.TokenKey;
import com.kailaisi.pojo.User;
import com.kailaisi.utils.ApiException;
import com.kailaisi.utils.CodeEnums;
import com.kailaisi.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenMapper tokenMapper;

    public List<User> findUsers() throws Exception {
        List<User> list = userMapper.selectByExample(null);
        return list;
    }

    @Override
    public User register(User bean) {
        List<User> users = userMapper.findByName(bean.getUsername());
        if (users.isEmpty()) {
            Integer id = userMapper.insertSelective(bean);
            User user1 = userMapper.selectByPrimaryKey(id);
            return user1;
        } else {
            throw new ApiException(CodeEnums.USER_EXIST);
        }
    }

    @Override
    public String login(String phone, String pwd) {
        List<User> users = userMapper.selectByNameAndPwd(phone, pwd);
        if (users.isEmpty()) {
            throw new ApiException(CodeEnums.USER_NOT_EXIST);
        } else {
            saveOrUpdateToken(users.get(0));
            return "登录成功";
        }
    }

    private void saveOrUpdateToken(User user) {
        String token = null;
        try {
            token = MD5.encryptMD5(String.valueOf(System.currentTimeMillis() + "appservice.02154778ke783dad34"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TokenKey tokenBean = tokenMapper.isTokenAvailable(user.getUsername());
        if(tokenBean!=null){
            tokenBean.setToken(token);
        }else {
            tokenBean=new TokenKey();
            tokenBean.setToken(token);
            tokenBean.setUsername(user.getUsername());
        }
        tokenMapper.updateOrAdd(tokenBean);
    }
}
