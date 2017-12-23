package com.kailaisi.service;

import com.kailaisi.bean.BaseBean;

import java.util.HashMap;

public interface PayService {
    BaseBean createOrder(int userId, String payGoods, Long payMoney, int payWay, String subject);

    String verifyPay(HashMap<String, String> params);
}
