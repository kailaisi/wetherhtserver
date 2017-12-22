package com.kailaisi.pojo;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer userid;

    private String phone;

    private String orderno;

    private Long paymoney;

    private String paygoods;

    private Integer status;

    private Date paytime;

    private String payway;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Long getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Long paymoney) {
        this.paymoney = paymoney;
    }

    public String getPaygoods() {
        return paygoods;
    }

    public void setPaygoods(String paygoods) {
        this.paygoods = paygoods == null ? null : paygoods.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway == null ? null : payway.trim();
    }
}