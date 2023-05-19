package com.celiaKey.orders.mvc.req.user;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class UserReq {
    @Valid
    @NotBlank
    private String userName;

    @Valid
    @NotBlank
    private String pwd;

    private int deptId;
}
