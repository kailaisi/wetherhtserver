package com.kailaisi.utils;

import com.kailaisi.bean.RestFulBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@Controller
public class GlableExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestFulBean<String> defaultException(Exception e) throws Exception {
        RestFulBean<Object> bean = new RestFulBean<Object>();
        if (e instanceof ApiException) {
            ApiException ae = (ApiException) e;
            return RestFulUtil.getInstance().getResuFulBean(ae.getData(), ae.getCode(), ae.getErrMsg());
        } else {
            return RestFulUtil.getInstance().getResuFulBean(null, CodeEnums.SYSTEM_ERR.getCode(), CodeEnums.SYSTEM_ERR.getMsg());
        }
    }
}
