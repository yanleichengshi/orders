package com.celiaKey.orders.exception;

import com.celiaKey.orders.config.ErrorEnum;
import com.celiaKey.orders.config.ResultCode;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class StoreException extends RuntimeException {
    private int errorCode;

    private String message;

    public StoreException(ErrorEnum resultCode) {
        this.errorCode = resultCode.getCode();
        this.message = resultCode.getMsg();
    }
}
