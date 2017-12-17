package com.kailaisi.utils;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.kailaisi.bean.RestFulBean;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * 全局返回Restful格式数据
 */
public class GlobalMessageConverter extends FastJsonHttpMessageConverter {
    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (obj instanceof RestFulBean) {
            super.writeInternal(obj, outputMessage);
            return;
        }
        RestFulBean bean = RestFulUtil.getInstance().getResuFulBean(obj, 0, "操作成功");
        super.writeInternal(bean, outputMessage);
    }
}
