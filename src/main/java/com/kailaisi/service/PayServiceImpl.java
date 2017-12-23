package com.kailaisi.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.kailaisi.bean.AliPayBean;
import com.kailaisi.bean.BaseBean;
import com.kailaisi.bean.WxPayBean;
import com.kailaisi.mapper.OrderMapper;
import com.kailaisi.pojo.Order;
import com.kailaisi.utils.ApiException;
import com.kailaisi.utils.CodeEnums;
import com.kailaisi.utils.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

import static com.alipay.api.AlipayConstants.APP_ID;
import static com.alipay.api.AlipayConstants.CHARSET;

@Service("payService")
public class PayServiceImpl implements PayService {
    // TODO: 2017/12/23 后期 对接支付宝需要的key值
    private static final String APP_PRIVATE_KEY = "ceshi";
    private static final String ALIPAY_PUBLIC_KEY = "ceshi";
    private final String lockSuo = "PayLock";
    @Resource
    private OrderMapper orderMapper;

    @Override
    public BaseBean createOrder(int userId, String payGoods, Long payMoney, int payWay, String subject) {
        String payTime = String.valueOf(System.currentTimeMillis());
        String orderNo = "";
        Order order = null;
        AliPayBean aliPayBean = null;
        WxPayBean wxPayBean = null;
        synchronized (lockSuo) {
            try {
                orderNo = MD5.encryptMD5(userId + payTime + payMoney + subject + "md5_create_order");
                Order orderBean = new Order();
                orderBean.setOrderno(orderNo);
                orderBean.setUserid(userId);
                orderBean.setPaygoods(payGoods);
                orderBean.setPaymoney(payMoney);
                orderBean.setStatus(0);//0：未支付 1：已支付
                orderBean.setPayway(payWay);
                if (payWay == 1) {
                    //实例化客户端
                    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
                    //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
                    AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
                    //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
                    AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
                    model.setBody("我是测试数据");
                    model.setSubject("App支付测试Java");
                    model.setOutTradeNo(orderNo);
                    model.setTimeoutExpress("30m");
                    model.setTotalAmount("0.01");
                    model.setProductCode("QUICK_MSECURITY_PAY");
                    request.setBizModel(model);
                    request.setNotifyUrl("http://10.50.50.205:8080/pay/verifyalipayresult.do");
                    try {
                        //这里和普通的接口调用不同，使用的是sdkExecute
                        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
                        aliPayBean = new AliPayBean();
                        aliPayBean.setOrderId(response.getOutTradeNo());
                        aliPayBean.setOrderInfo(response.getBody());
                    } catch (AlipayApiException e) {
                        e.printStackTrace();
                    }
                } else if (payWay == 2) {
                    // TODO: 2017/12/23 微信支付待开发
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (payWay == 1) {
            if (aliPayBean != null) {
                return aliPayBean;
            } else {
                throw new ApiException(CodeEnums.ORDER_CREAT_FAILED);
            }
        } else if (payWay == 2) {
            if (wxPayBean != null) {
                return wxPayBean;
            } else {
                throw new ApiException(CodeEnums.ORDER_CREAT_FAILED);
            }
        } else {
            throw new ApiException(CodeEnums.ORDER_CREAT_FAILED);
        }
    }

    @Override
    public String verifyPay(HashMap<String, String> params) {
        Order order = orderMapper.selectByOrerNo(params.get("out_trade_no"));
        if (order != null) {
            order.setStatus(2);
            orderMapper.updateByPrimaryKey(order);
            return "success";
        }
        return "failed";
    }
}
