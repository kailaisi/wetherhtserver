package com.kailaisi.controller;

import com.kailaisi.bean.BaseBean;
import com.kailaisi.service.PayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller()
@RequestMapping("/pay")
public class PayAction {
    @Resource
    PayService payService;

    @ResponseBody
    @RequestMapping(value = "/createOrder.do", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public BaseBean createOrder(int userId, String payGoods, Long payMoney, int payWay, String subject) {
        return payService.createOrder(userId, payGoods, payMoney, payWay, subject);
    }

    @ResponseBody
    @RequestMapping(value = "/verifyPay.do", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public String verifyPay(HashMap<String, String> params) {
        return payService.verifyPay(params);
    }
}
