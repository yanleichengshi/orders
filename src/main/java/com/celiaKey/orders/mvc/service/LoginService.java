package com.celiaKey.orders.mvc.service;

import com.celiaKey.orders.mvc.entity.Dept;
import com.celiaKey.orders.mvc.entity.User;

import java.util.List;

public interface LoginService {
    /**
     * 注册
     * @param user
     */
    void register(User user);

    /**
     * 查询部门及部门下的用户
     * @return 部门及用户
     */
    List<Dept> queryDeptAndOfUser();

}
