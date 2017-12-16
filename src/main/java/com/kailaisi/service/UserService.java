package com.kailaisi.service;

import com.kailaisi.bean.RestFulBean;
import com.kailaisi.bean.UserBean;
import com.kailaisi.dao.UserDao;
import com.kailaisi.utils.RestFulUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserDao userDao;

    public RestFulBean<UserBean> registorServer(UserBean userBean) {
        UserBean user = userDao.getUser(userBean.getPhone());
        if (user != null) {
            return RestFulUtil.getInstance().getResuFulBean(null, 1, "已经注册过了");
        } else {
            user = userDao.register(userBean);
            if (user != null) {
                return RestFulUtil.getInstance().getResuFulBean(user, 0, "注册成功");
            } else {
                return RestFulUtil.getInstance().getResuFulBean(null, 1, "注册失败");
            }
        }

    }

    public RestFulBean<UserBean> login(String phone, String password) {
        UserBean user = userDao.login(phone, password);
        if (user != null) {
            return RestFulUtil.getInstance().getResuFulBean(user, 0, "登录成功");
        } else {
            return RestFulUtil.getInstance().getResuFulBean(null, 1, "用户不存在");
        }
    }

}  