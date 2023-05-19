package com.celiaKey.orders.mvc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private String pwd;
    private int status;
    private int deptId;
    private int delFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
