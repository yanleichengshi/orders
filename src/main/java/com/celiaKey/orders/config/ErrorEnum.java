package com.celiaKey.orders.config;

/**
 * 异常枚举类
 */
public enum ErrorEnum {
    ADD_FAIL(50001, "新增失败"),
    DEL_FAIL(50002, "删除失败"),
    EDIT_FAIL(50003, "修改失败"),
    ILLEGAL_PARAM(60001, "参数异常"),
    ;

    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
