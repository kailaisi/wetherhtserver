package com.kailaisi.bean;

/**
 * 支付宝生成订单号以后回传给支付端的数据
 */
public class AliPayBean extends BaseBean {
    private String orderId;
    private String orderInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
