package com.celiaKey.orders.mvc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.celiaKey.orders.config.ErrorEnum;
import com.celiaKey.orders.mvc.dao.BannerDao;
import com.celiaKey.orders.mvc.entity.Banner;
import com.celiaKey.orders.exception.StoreException;
import com.celiaKey.orders.mvc.service.BannerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BannerServiceImpl implements BannerService {
    private static final String BANNER = "banner";

    @Resource
    private BannerDao bannerDao;

    @Override
    public Boolean addBanner(Banner banner) {
        Boolean result = bannerDao.addBanner(banner);
        if (result) {
            log.info("addBanner success");
        } else {
            log.error("addBanner failed");
        }
        return result;
    }

    @Override
    public Boolean delBanner(int dataId) {
        return bannerDao.delBanner(dataId);
    }

    @Override
    public Boolean updateBanner(Banner banner) {
        return bannerDao.updateBanner(banner);
    }

    @Override
    public Optional<Banner> getBannerById(Integer id) {
        Banner banner = bannerDao.getBannerById(id);
        return Optional.ofNullable(banner);
    }

    @Override
    public List<Banner> queryList(int start, int page) {
        return bannerDao.queryList(start, page * 10);
    }

    @Override
    public void batchAdd(JSONObject jsonObject) {
        if (jsonObject.isEmpty() || !jsonObject.containsKey(BANNER)) {
            throw new StoreException(ErrorEnum.ILLEGAL_PARAM);
        }
        JSONArray jsonArray = jsonObject.getJSONArray(BANNER);
        if (CollectionUtils.isEmpty(jsonArray)) {
            throw new StoreException(ErrorEnum.ILLEGAL_PARAM);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Banner banner = jsonObj.toJavaObject(Banner.class);
            Banner bannerResult = bannerDao.getBannerById(banner.getId());
            if (bannerResult == null) {
                bannerDao.addBannerById(banner);
                log.error("add banner success {}" + banner);
                continue;
            }

            // 比较是否一致
            if (!compareBanner(banner, bannerResult)) {
                // 不一致则更新
                bannerDao.updateBanner(banner);
            }
        }
    }

    private boolean compareBanner(Banner banner, Banner bannerResult) {
        if (!banner.getAuthorId().equals(bannerResult.getAuthorId())) {
            return false;
        }
        if (!banner.getTitle().equals(bannerResult.getTitle())) {
            return false;
        }
        if (!banner.getContent().equals(bannerResult.getContent())) {
            return false;
        }
        if (!banner.getPath().equals(bannerResult.getPath())) {
            return false;
        }
        return true;
    }
}
