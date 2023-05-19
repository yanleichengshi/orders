package com.celiaKey.orders.mvc.entity;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * t_banner 实体类
 */
@Data
public class Banner {
    @JSONField(name = "id")
    private int id;

    @JSONField(name = "authorId")
    private String authorId;

    @JSONField(name = "title")
    private String title;

    @JSONField(name = "content")
    private String content;

    @JSONField(name = "path")
    private String path;

    private String createTime;

    private String updateTime;
}
