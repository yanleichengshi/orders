package com.celiaKey.orders.listener;

import com.celiaKey.orders.config.BannerConfig;
import com.celiaKey.orders.mvc.service.BannerService;
import com.celiaKey.orders.utils.SpringBeanUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 监听工厂
 */
@Component
public class FileListenerFactory {
    /**
     * 设置轮询间隔
     */
    private final long interval = TimeUnit.SECONDS.toMillis(1);

    // 业务
    @Autowired
    private BannerService bannerService;

    /**
     * 获取监听器
     * @return 监听器
     */
    public FileAlterationMonitor getMonitor() {
        // 创建过滤器
        IOFileFilter dir = FileFilterUtils.and(FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".json"));
        IOFileFilter filter = FileFilterUtils.or(dir, files);

        // 设置监听路径
        BannerConfig banner = SpringBeanUtils.getBean(BannerConfig.class);
        String monitorDir = banner.getMonitorDir();

        // 配置过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir), filter);

        // 向监听者添加监听器，并注入业务
        observer.addListener(new FileListener(bannerService));

        // 返回监听者
        return new FileAlterationMonitor(interval, observer);
    }
}
