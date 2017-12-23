package com.kailaisi.utils;

public enum CodeEnums {
    UCCESS(0, "操作成功"),
    SYSTEM_ERR(1, "系统异常"),
    PARA_ERR(2, "[参数异常]:"),
    USER_EXIST(10001, "用户已经注册"),
    PWD_CHECK_ERR(10002, "重复密码有误"),
    USER_NOT_EXIST(10003, "用户不存在"),
    USER_IS_LOCK(10004, "用户状态被锁定"),
    USER_PWD_FAIL(10005, "登录密码错误"),
    USER_TOKEN_FAIL(10006, "鉴权失败,请重新登录"),
    USER_TOKEN_EMPTY(10007, "获取鉴权信息失败"),
    USER_ROLE_LIMIT(10008, "权限不足"),
    EMAIL_USED(10009, "邮箱已经被使用"),
    NAME_NOT_ALLOWED(10010, "用户名必须为手机号"),
    ORDER_CREAT_FAILED(10011,"订单生成失败"),
    //
    ARTICLE_NOT_EXIST(20001, "文章不存在"),;

    CodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
