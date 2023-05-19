package com.celiaKey.orders.mvc.resp;

import com.celiaKey.orders.config.ResultCode;

import lombok.Builder;
import lombok.Data;

/**
 * 统一响应
 */
@Data
@Builder
public class ResultResp {
    private Integer code;

    private String message;

    private Object data;

    /**
     * 成功响应
     * @param param
     * @return
     */
    public static ResultResp success(Object param) {
        ResultResp resultResp = ResultResp.builder()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage())
                .data(param).build();
        return resultResp;
    }

    /**
     * 失败响应
     * @param message
     * @return
     */
    public static ResultResp failed(String message) {
        ResultResp resultResp = ResultResp.builder()
                .code(ResultCode.FAILED.getCode())
                .message(message != null ? message : ResultCode.FAILED.getMessage())
                .data(null).build();
        return resultResp;
    }
}
