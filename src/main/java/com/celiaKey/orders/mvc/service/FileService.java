package com.celiaKey.orders.mvc.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    /**
     * 上传excel文件至服务器并解析数据入库
     * @param file
     */
    void upload(MultipartFile file);

    /**
     * 生成excel文件传递给前端
     * @param response
     */
    void downloadExcel(HttpServletResponse response);

    /**
     * 下载文件Txt
     * @param response
     */
    void downloadTxt(HttpServletResponse response);

    void getQRCode(String content, HttpServletResponse response);

    void getVerifyCode(HttpServletResponse response);
}
