package com.celiaKey.orders.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 */
@Slf4j
@Service
public class FileUtils {
    /**
     * 解析文件成JSONObject数据
     * @param file
     * @return
     */
    public static JSONObject parseFile(File file) {
        try {
            String json = org.apache.commons.io.FileUtils.readFileToString(file, "UTF-8");
            return JSONObject.parseObject(json);
        } catch (IOException e) {
            log.error("parseFile method failed...");
            return new JSONObject();
        }
    }

    public static void stringToFile(File file, String str) {
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(file, str, "UTF-8");
        } catch (IOException e) {
            log.error("stringToFile method failed...");
        }
    }
}
