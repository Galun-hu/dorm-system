package com.joy.dorm.common.utils;

public class RespResult {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespResult build() {
        return new RespResult();
    }

    public static RespResult ok(String msg) {
        return new RespResult(200, msg, null);
    }

    public static RespResult ok(String msg, Object obj) {
        return new RespResult(200, msg, obj);
    }

    public static RespResult error(String msg) {
        return new RespResult(500, msg, null);
    }

    public static RespResult error(String msg, Object obj) {
        return new RespResult(500, msg, obj);
    }

    public RespResult() {
    }

    public RespResult(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
