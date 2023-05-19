package com.celiaKey.orders.mvc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Dept {
    private int id;
    private String name;
    private int state;
    private int delFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 关联部门下用户信息
    List<User> userList;
}
