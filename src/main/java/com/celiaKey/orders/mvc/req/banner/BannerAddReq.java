package com.celiaKey.orders.mvc.req.banner;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class BannerAddReq {
    @NotNull
    private String authorId;

    @NotNull
    private String title;

    @NotNull
    @Length(min = 1)
    private String content;

    @NotNull
    private String path;
}
