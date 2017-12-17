package com.kailaisi.utils;

import com.kailaisi.bean.RestFulBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlableExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestFulBean<String> defaultException(HttpServletRequest request, Exception e) {
        return RestFulUtil.getInstance().getResuFulBean(null, -1, e.getMessage());
    }
}
