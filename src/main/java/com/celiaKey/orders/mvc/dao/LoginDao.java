package com.celiaKey.orders.mvc.dao;


import com.celiaKey.orders.mvc.entity.Dept;
import com.celiaKey.orders.mvc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * banner.dao
 */
@Mapper
public interface LoginDao {
    /**
     * 注册
     * @param user
     */
    void register(@Param("user") User user);

    /**
     * 查询部门及部门下的用户
     * @return 部门及用户
     */
    List<Dept> queryDeptAndOfUser();

}
