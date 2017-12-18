package com.kailaisi.utils;

public class ApiException extends RuntimeException {
    private Integer code;
    private String errMsg;
    private Object data;

    public ApiException(Integer code, String errMsg, Object data) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
    }
    public ApiException(Integer code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
    public ApiException(CodeEnums codeEnums) {
        super(codeEnums.getMsg());
        this.code = codeEnums.getCode();
        this.errMsg = codeEnums.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
