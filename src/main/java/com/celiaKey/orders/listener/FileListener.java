package com.celiaKey.orders.listener;

import com.alibaba.fastjson.JSONObject;
import com.celiaKey.orders.config.BannerConfig;
import com.celiaKey.orders.mvc.service.BannerService;
import com.celiaKey.orders.utils.FileUtils;
import com.celiaKey.orders.utils.SpringBeanUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * 文件监听业务类
 */
public class FileListener extends FileAlterationListenerAdaptor {
    // 业务
    private BannerService bannerService;

    /**
     * 构造方法
     * @param bannerService
     */
    public FileListener(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @Override
    public void onFileChange(File file) {
        BannerConfig banner = SpringBeanUtils.getBean(BannerConfig.class);
        if (banner.getFileName().equals(file.getName())) {
            JSONObject jsonObject = FileUtils.parseFile(file);
            bannerService.batchAdd(jsonObject);
        }
        super.onFileChange(file);
    }
}
