package com.celiaKey.orders.mvc.dao;

import com.celiaKey.orders.mvc.entity.Banner;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * banner.dao
 */
@Mapper
public interface BannerDao {
    /**
     * 新增banner
     */
    Boolean addBanner(Banner banner);

    /**
     * 删除指定id的banner
     * @param dataId
     * @return
     */
    Boolean delBanner(int dataId);

    /**
     * 跟新banner
     * @param banner
     */
    Boolean updateBanner(Banner banner);

    /**
     * 根据id查询banner详情
     * @param id
     * @return
     */
    Banner getBannerById(Integer id);

    /**
     * 分页查询
     * @param start
     * @param page
     * @return
     */
    List<Banner> queryList(int start, int page);

    /**
     * 新增给定id的banner
     * @param banner
     */
    void addBannerById(Banner banner);

    List<Banner> queryAllBanner();
}
