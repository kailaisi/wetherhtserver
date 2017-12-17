package com.kailaisi.utils;

import com.kailaisi.bean.RestFulBean;

public class RestFulUtil<T> {
    public static RestFulUtil getInstance() {
        return new RestFulUtil();
    }

    public RestFulBean<T> getResuFulBean(T bean, int status, String msg) {
        RestFulBean<T> bean1 = new RestFulBean<T>();
        bean1.setData(bean);
        bean1.setMsg(msg);
        bean1.setStatus(status);
        return bean1;
    }
}
