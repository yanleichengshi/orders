package com.celiaKey.orders.mvc.controller;

import com.celiaKey.orders.config.ErrorEnum;
import com.celiaKey.orders.mvc.entity.Dept;
import com.celiaKey.orders.mvc.entity.User;
import com.celiaKey.orders.mvc.req.user.UserReq;
import com.celiaKey.orders.mvc.resp.ResultResp;
import com.celiaKey.orders.mvc.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * 注册
     * @param req
     * @return
     */
    @RequestMapping("/register")
    public ResultResp register(@Valid @RequestBody UserReq req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        loginService.register(user);
        return ResultResp.failed(ErrorEnum.ADD_FAIL.getMsg());
    }

    /**
     * 查询部门及部门下的用户
     * @return 部门及用户
     */
    @RequestMapping("/query")
    public ResultResp query() {
        List<Dept> depts =  loginService.queryDeptAndOfUser();
        return ResultResp.success(depts);
    }
}
