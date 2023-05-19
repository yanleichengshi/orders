package com.celiaKey.orders.mvc.req.banner;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BannerDelReq {
    @NotNull
    private int dataId;
}
