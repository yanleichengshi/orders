package com.celiaKey.orders.mvc.service.impl;

import cn.hutool.crypto.symmetric.AES;
import com.celiaKey.orders.config.CommonCons;
import com.celiaKey.orders.mvc.dao.LoginDao;
import com.celiaKey.orders.mvc.entity.Dept;
import com.celiaKey.orders.mvc.entity.User;
import com.celiaKey.orders.mvc.service.LoginService;
import com.celiaKey.orders.utils.AESUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginDao loginDao;

    @Override
    public void register(User user) {
        String newPwd = AESUtils.encrypt(user.getPwd(), CommonCons.PWD_SALT);
        user.setPwd(newPwd);
        user.setDelFlag(1);
        loginDao.register(user);
    }

    @Override
    public List<Dept> queryDeptAndOfUser() {
        return loginDao.queryDeptAndOfUser();
    }
}
