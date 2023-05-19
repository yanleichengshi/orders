package com.celiaKey.orders.mvc.controller;

import com.celiaKey.orders.mvc.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    /**
     * 上传excel文件至服务器并解析数据入库
     * @param file
     */
    @RequestMapping("/upload")
    public void upload(MultipartFile file) {
        fileService.upload(file);
    }

    /**
     * 下载文件Excel
     * @param response
     */
    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) {
        fileService.downloadExcel(response);
    }

    /**
     * 下载文件Txt
     * @param response
     */
    @RequestMapping("/downloadTxt")
    public void downloadTxt(HttpServletResponse response) {
        fileService.downloadTxt(response);
    }

    /* 创建二维码文件并且返回给前端 */
    @RequestMapping("/getQRCode")
    public void getQRCode(@RequestParam String content, HttpServletResponse response) {
        fileService.getQRCode(content, response);
    }

    /* 创建验证码文件并且返回给前端 */
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletResponse response) {
        fileService.getVerifyCode(response);
    }
}
