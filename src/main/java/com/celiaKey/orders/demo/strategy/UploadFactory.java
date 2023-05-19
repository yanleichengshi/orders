package com.celiaKey.orders.demo.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式--工厂类，初始化对应的实现类
 */
public class UploadFactory {
    private static final Map<String, BaseUpload> map = new HashMap<>();

    static {
        map.put("localUpload", new LocalUpload());
        map.put("remoteUpload", new RemoteUpload());
    }

    public static BaseUpload getUpload(String type) {
        return map.get(type);
    }
}
