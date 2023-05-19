package com.celiaKey.orders.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制台打印jar版本和编译时间
 */
@Slf4j
@Configuration
public class MavenInfoUtils {

    // 当前版本
    @Value("${app.version}")
    private String serviceVersion;

    // 打包时间
    @Value("${app.build.time}")
    private String serviceBuildDate;

    @PostConstruct
    public void projectInfo(){
        StringBuffer projectInfo = new StringBuffer();
        projectInfo.append("\n=================project=================\n");
        projectInfo.append(String.format("\nservice version:%s\n",serviceVersion));
        String timestamps = formatTimeHours(serviceBuildDate, 8);
        projectInfo.append(String.format("\nservice build date:%s\n",timestamps));
        projectInfo.append("\n=================project=================\n");
        log.info(projectInfo.toString());
    }
    /**
     * 将时间转换为东八区时区，默认为东0区
     *
     * @param time
     * @param hours
     * @return
     */
    public String formatTimeHours(String time, int hours) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sd.parse(time);
            long rightTime = (long) (d.getTime() + hours * 60 * 60 * 1000);
            String newtime = sd.format(rightTime);
            return newtime;
        } catch (Exception e) {
            return null;
        }
    }
}
