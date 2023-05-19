package com.celiaKey.orders.mvc.service;

import com.alibaba.fastjson.JSONObject;

import com.celiaKey.orders.mvc.entity.Banner;

import java.util.List;
import java.util.Optional;

public interface BannerService {
    /**
     * 新增banner
     * @param banner
     * @return
     */
    Boolean addBanner(Banner banner);

    /**
     * 删除指定id的banner
     * @param dataId
     * @return
     */
    Boolean delBanner(int dataId);

    /**
     * 修改banner
     * @param banner
     * @return
     */
    Boolean updateBanner(Banner banner);

    /**
     * 根据id查询banner详情
     * @param id
     * @return
     */
    Optional<Banner> getBannerById(Integer id);

    /**
     * 分页查询
     * @param start
     * @param page
     * @return
     */
    List<Banner> queryList(int start, int page);

    /**
     * 批量新增banner
     * @param jsonObject
     */
    void batchAdd(JSONObject jsonObject);
}
