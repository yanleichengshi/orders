package com.celiaKey.orders.mvc.controller;

import com.celiaKey.orders.config.ErrorEnum;
import com.celiaKey.orders.mvc.entity.Banner;
import com.celiaKey.orders.mvc.req.banner.BannerAddReq;
import com.celiaKey.orders.mvc.req.banner.BannerDelReq;
import com.celiaKey.orders.mvc.req.banner.BannerQueryReq;
import com.celiaKey.orders.mvc.req.banner.BannerUpdateReq;
import com.celiaKey.orders.mvc.resp.ResultResp;
import com.celiaKey.orders.mvc.service.BannerService;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * banner.controller
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private BannerService bannerService;

    @RequestMapping("/addBanner")
    public ResultResp addBanner(@RequestBody BannerAddReq bannerAddReq) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(bannerAddReq, banner);
        Boolean result = bannerService.addBanner(banner);
        if (result) {
            return ResultResp.success(banner);
        }
        return ResultResp.failed(ErrorEnum.ADD_FAIL.getMsg());
    }

    @RequestMapping("/delBanner")
    public ResultResp delBanner(@RequestBody BannerDelReq request) {
        Boolean result = bannerService.delBanner(request.getDataId());
        if (result) {
            return ResultResp.success(request.getDataId());
        }
        return ResultResp.failed(ErrorEnum.DEL_FAIL.getMsg());
    }

    @RequestMapping("/updateBanner")
    public ResultResp updateBanner(@RequestBody BannerUpdateReq request) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(request, banner);
        Boolean result = bannerService.updateBanner(banner);
        if (result) {
            return ResultResp.success(banner);
        }
        return ResultResp.failed(ErrorEnum.EDIT_FAIL.getMsg());
    }

    @RequestMapping("/getBanner")
    public ResultResp getBanner(@RequestParam Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultResp.failed(ErrorEnum.ILLEGAL_PARAM.getMsg());
        }
        Optional<Banner> banner = bannerService.getBannerById(id);
        if (banner.isPresent()) {
            return ResultResp.success(banner.get());
        }
        return ResultResp.failed("该条数据不存在");
    }

    @RequestMapping("/queryBanner")
    public ResultResp queryBanner(@RequestBody BannerQueryReq request) {
        List<Banner> banners = bannerService.queryList(request.getStart(), request.getPage());
        if (CollectionUtils.isEmpty(banners)) {
            return ResultResp.failed("查询失败!!!!");
        } else {
            return ResultResp.success(banners);
        }
    }
}
