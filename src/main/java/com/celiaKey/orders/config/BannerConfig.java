package com.celiaKey.orders.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * banner配置类
 */
@Data
@Component
@PropertySource(value = {"classpath:banner.properties"})
public class BannerConfig {
    @Value("${banner.monitorDir}")
    private String monitorDir;

    @Value("${banner.fileName}")
    private String fileName;
}
