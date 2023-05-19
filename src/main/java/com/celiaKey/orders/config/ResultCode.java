package com.celiaKey.orders.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一响应枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(60200, "成功"),

    FAILED(60400, "失败"),
    ;

    private Integer code;

    private String message;
}
