package com.kailaisi.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kailaisi.bean.RestFulBean;
import com.kailaisi.mapper.TokenMapper;
import com.kailaisi.pojo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器，任何业务，先判断token是否一致，一致以后再进一步处理
 */
public class RestFulInterceptor implements HandlerInterceptor {
    @Autowired
    TokenMapper tokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        Map<String, String> headMaps = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            headMaps.put(key, value);
        }
        if (uri.endsWith(".do")) {
            return true;
        } else {
            if (headMaps.get("username") == null || headMaps.get("token") == null) {
                return verifyError(response);
            }
            Token tokenKeys = tokenMapper.isTokenAvailable(headMaps.get("username"));
            if (tokenKeys != null && tokenKeys.getToken().endsWith(headMaps.get("token"))) {
                return true;
            } else {
                return verifyError(response);
            }
        }
    }

    /**
     * 认证失败
     */
    private boolean verifyError(HttpServletResponse response) throws IOException {
        RestFulBean<Token> resuFulBean = RestFulUtil.getInstance().getResuFulBean(null, 1, "用户身份验证失败");
        response.setCharacterEncoding("UTF-8");
        Writer writer = response.getWriter();
        writer.write(JSONObject.toJSONString(resuFulBean, SerializerFeature.WriteMapNullValue));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
