package com.celiaKey.orders.mvc.req.banner;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BannerQueryReq {
    private int start;

    @NotNull
    @Min(value = 1)
    private int page;
}
